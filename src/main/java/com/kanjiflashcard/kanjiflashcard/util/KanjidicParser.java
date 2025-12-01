package com.kanjiflashcard.kanjiflashcard.util;

import com.kanjiflashcard.kanjiflashcard.model.Kanji;
import com.kanjiflashcard.kanjiflashcard.repository.KanjiRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

@Component
public class KanjidicParser {

    private final KanjiRepository repository;

    public KanjidicParser(KanjiRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void loadKanjidic() {
        try {
            // Load kanjidic2.xml from resources
            ClassPathResource resource = new ClassPathResource("kanjidic2.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(resource.getInputStream());

            doc.getDocumentElement().normalize();

            // Get the first <character> node
            Node firstCharacter = doc.getElementsByTagName("character").item(0);

            if (firstCharacter != null && firstCharacter.getNodeType() == Node.ELEMENT_NODE) {
                Element characterElement = (Element) firstCharacter;

                // ----- Extract literal -----
                String literal = characterElement
                        .getElementsByTagName("literal")
                        .item(0)
                        .getTextContent();

                // ----- Extract meaning (first one only) -----
                NodeList meaningList = characterElement
                        .getElementsByTagName("meaning");

                String meaning = null;
                for (int i = 0; i < meaningList.getLength(); i++) {
                    Element m = (Element) meaningList.item(i);
                    if (!m.hasAttribute("m_lang")) {   // Only take English
                        meaning = m.getTextContent();
                        break;
                    }
                }

                // ----- Extract onyomi -----
                String onyomi = "";
                NodeList readingList = characterElement.getElementsByTagName("reading");
                for (int i = 0; i < readingList.getLength(); i++) {
                    Element r = (Element) readingList.item(i);
                    if ("ja_on".equals(r.getAttribute("r_type"))) {
                        onyomi += r.getTextContent() + ",";
                    }
                }
                if (onyomi.endsWith(",")) onyomi = onyomi.substring(0, onyomi.length() - 1);

                // ----- Extract kunyomi -----
                String kunyomi = "";
                for (int i = 0; i < readingList.getLength(); i++) {
                    Element r = (Element) readingList.item(i);
                    if ("ja_kun".equals(r.getAttribute("r_type"))) {
                        kunyomi += r.getTextContent() + ",";
                    }
                }
                if (kunyomi.endsWith(",")) kunyomi = kunyomi.substring(0, kunyomi.length() - 1);

                // ----- Extract stroke count -----
                int strokes = Integer.parseInt(
                        characterElement.getElementsByTagName("stroke_count")
                                .item(0)
                                .getTextContent()
                );

                // ----- Save to DB -----
                Kanji kanji = new Kanji(literal, meaning, onyomi, kunyomi, strokes);
                repository.save(kanji);

                System.out.println("Loaded first kanji: " + literal);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}