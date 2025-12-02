package com.kanjiflashcard.kanjiflashcard.util;

import com.kanjiflashcard.kanjiflashcard.model.Kanji;
import com.kanjiflashcard.kanjiflashcard.repository.KanjiRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.w3c.dom.*;

import java.util.ArrayList;
import java.util.List;

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
            // Load the XML
            ClassPathResource resource = new ClassPathResource("kanjidic2.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(resource.getInputStream());
            doc.getDocumentElement().normalize();

            NodeList characters = doc.getElementsByTagName("character");
            System.out.println("Found characters: " + characters.getLength());

            for (int i = 0; i < characters.getLength(); i++) {
                Node node = characters.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element characterElement = (Element) node;

                    // literal
                    String literal = characterElement
                            .getElementsByTagName("literal")
                            .item(0)
                            .getTextContent();

                    // meaning (first English meaning)
                    NodeList meaningList = characterElement.getElementsByTagName("meaning");
                    List<String> englishMeanings = new ArrayList<>();

                    for (int m = 0; m < meaningList.getLength(); m++) {
                        Element el = (Element) meaningList.item(m);
                        
                        if (!el.hasAttribute("m_lang")) { // English only
                            englishMeanings.add(el.getTextContent());
                        }
                    }

                    if (englishMeanings.isEmpty()) {
                        continue; // skip if no English meaning
                    }

                    String meanings = String.join(",", englishMeanings);

                    // onyomi
                    StringBuilder onyomi = new StringBuilder();
                    // kunyomi
                    StringBuilder kunyomi = new StringBuilder();

                    NodeList readings = characterElement.getElementsByTagName("reading");
                    for (int r = 0; r < readings.getLength(); r++) {
                        Element rEl = (Element) readings.item(r);
                        String type = rEl.getAttribute("r_type");
                        if ("ja_on".equals(type)) {
                            onyomi.append(rEl.getTextContent()).append(",");
                        } else if ("ja_kun".equals(type)) {
                            kunyomi.append(rEl.getTextContent()).append(",");
                        }
                    }

                    // Remove trailing comma
                    if (onyomi.length() > 0) onyomi.setLength(onyomi.length() - 1);
                    if (kunyomi.length() > 0) kunyomi.setLength(kunyomi.length() - 1);

                    // stroke count
                    int strokes = Integer.parseInt(
                            characterElement.getElementsByTagName("stroke_count")
                                    .item(0)
                                    .getTextContent()
                    );

                    // Save to DB
                    Kanji kanji = new Kanji(
                            literal,
                            meanings,
                            onyomi.toString(),
                            kunyomi.toString(),
                            strokes
                    );

                    repository.save(kanji);
                }
            }

            System.out.println("Loaded kanji count: " + repository.count());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}