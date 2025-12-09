package com.kanjiflashcard.kanjiflashcard.util;

import com.kanjiflashcard.kanjiflashcard.model.Kanji;
import com.kanjiflashcard.kanjiflashcard.repository.KanjiRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Component that loads Joyo kanji from CSV into the database.
 */
@Component
public final class JoyoCsvLoader {

    /** CSV column index for literal. */
    private static final int COL_LITERAL = 1;

    /** CSV column index for stroke count. */
    private static final int COL_STROKE_COUNT = 4;

    /** CSV column index for meanings. */
    private static final int COL_MEANINGS = 7;

    /** CSV column index for onyomi. */
    private static final int COL_ONYOMI = 8;

    /** CSV column index for kunyomi. */
    private static final int COL_KUNYOMI = 9;

    /** CSV column index for frequency. */
    private static final int COL_FREQUENCY = 10;

    /** CSV column index for JLPT level. */
    private static final int COL_JLPT_LEVEL = 11;

    /** Repository to interact with Kanji data. */
    private final KanjiRepository kanjiRepository;

    /**
     * Constructs a JoyoCsvLoader with the given repository.
     *
     * @param kanjiRepo the repository for Kanji data
     */
    public JoyoCsvLoader(final KanjiRepository kanjiRepo) {
        this.kanjiRepository = kanjiRepo;
    }

    /**
     * Loads Joyo kanji from joyo.csv into the database.
     */
    @PostConstruct
    public void loadJoyo() {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new ClassPathResource("joyo.csv").getInputStream(),
                        StandardCharsets.UTF_8))) {

            List<Kanji> kanjiList = new ArrayList<>();
            String line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] c = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                String literal = c[COL_LITERAL];
                String meanings = c[COL_MEANINGS];
                String onyomi = c[COL_ONYOMI];
                String kunyomi = c[COL_KUNYOMI];
                int strokeCount = Integer.parseInt(c[COL_STROKE_COUNT]);
                int frequency = Integer.parseInt(c[COL_FREQUENCY]);
                int jlptLevel = Integer.parseInt(c[COL_JLPT_LEVEL]);

                Kanji kanji = new Kanji(
                        literal,
                        meanings,
                        onyomi,
                        kunyomi,
                        strokeCount,
                        jlptLevel,
                        frequency);

                kanjiList.add(kanji);
            }

            kanjiRepository.saveAll(kanjiList);
            System.out.println("Loaded Joyo kanji count: "
                    + kanjiRepository.count());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
