package com.kanjiflashcard.kanjiflashcard.util;

import com.kanjiflashcard.kanjiflashcard.model.Kanji;
import com.kanjiflashcard.kanjiflashcard.repository.KanjiRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class JoyoCsvLoader {

    private final KanjiRepository kanjiRepository; // Repository to interact with Kanji data

    public JoyoCsvLoader(KanjiRepository kanjiRepository) {
        this.kanjiRepository = kanjiRepository; // Injects the KanjiRepository dependency
    }

     // Loads Joyo kanji from joyo.csv into the database
    @PostConstruct
    public void loadJoyo() {

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                new ClassPathResource("joyo.csv").getInputStream(),
                StandardCharsets.UTF_8))) {

            List<Kanji> kanjiList = new ArrayList<>(); // Create a new list to hold Kanji objects
            String line = br.readLine(); // Skips the CSV header

            while ((line = br.readLine()) != null) {
                String[] c = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); // Splits CSV line into fields

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

                kanjiList.add(kanji);

            } // Loops through each line of the CSV to get kanji data

            kanjiRepository.saveAll(kanjiList); // Saves all Kanji objects to the database
            System.out.println("Loaded Joyo kanji count: " + kanjiRepository.count()); // Prints the count of kanji loaded
            

        } catch (IOException e) {
            e.printStackTrace();

        }

    }
}