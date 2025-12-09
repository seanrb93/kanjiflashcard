package com.kanjiflashcard.kanjiflashcard.controller;

import com.kanjiflashcard.kanjiflashcard.model.Kanji;
import com.kanjiflashcard.kanjiflashcard.repository.KanjiRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.data.domain.PageRequest;

/**
 * REST controller for Kanji-related endpoints.
 */
@RestController
@RequestMapping("/api")
public final class KanjiController {

    /** Repository for Kanji data access. */
    private final KanjiRepository kanjiRepo;

    /**
     * Constructs a KanjiController with the given repository.
     *
     * @param kanjiRepository the repository for Kanji data
     */
    public KanjiController(final KanjiRepository kanjiRepository) {
        this.kanjiRepo = kanjiRepository;
    }

    /**
     * Returns a random Kanji, optionally filtered by JLPT level.
     *
     * @param jlpt the JLPT level to filter by, or null for any level
     * @return a random Kanji matching the criteria
     */
    @GetMapping("/random")
    public Kanji getRandomKanji(
            @RequestParam(required = false) final Integer jlpt) {

        if (jlpt == null) {
            long count = kanjiRepo.count();
            int index = (int) (Math.random() * count);
            return kanjiRepo.findAll(PageRequest.of(index, 1))
                    .getContent().get(0);
        }

        List<Kanji> kanjis = kanjiRepo.findByJlptLevel(jlpt);

        if (kanjis.isEmpty()) {
            return null;
        }

        int index = (int) (Math.random() * kanjis.size());
        return kanjis.get(index);
    }

}
