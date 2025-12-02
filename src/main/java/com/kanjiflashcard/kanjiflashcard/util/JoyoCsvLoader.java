package com.kanjiflashcard.kanjiflashcard.util;

import com.kanjiflashcard.kanjiflashcard.model.Kanji;
import com.kanjiflashcard.kanjiflashcard.repository.KanjiRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import java.io.*;
import java.nio.charset.StandardCharsets;

@Component
public class JoyoCsvLoader {

    private final KanjiRepository kanjiRepository;

    public JoyoCsvLoader(KanjiRepository kanjiRepository) {
        this.kanjiRepository = kanjiRepository;
    }

    @PostConstruct
    public void loadJoyo() {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new ClassPathResource("joyo.csv").getInputStream(),
                        StandardCharsets.UTF_8))) {
            String line = br.readLine(); // Skip header

            while ((line = br.readLine()) != null) {
                String[] c = line.split(",");

                String literal = c[1];
                String meanings = c[7];
                String onyomi = c[8];
                String kunyomi = c[9];
                int strokeCount = Integer.parseInt(c[4]);
                int frequency = Integer.parseInt(c[10]);
                int jlptLevel = Integer.parseInt(c[11]);

                Kanji kanji = new Kanji(
                        literal,
                        meanings,
                        onyomi,
                        kunyomi,
                        strokeCount,
                        jlptLevel,
                        frequency);

                kanjiRepository.save(kanji);

            }

            System.out.println("Loaded Joyo kanji count: " + kanjiRepository.count());

        } catch (IOException e) {
            e.printStackTrace();

        }

    }
}