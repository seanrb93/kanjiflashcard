package com.kanjiflashcard.kanjiflashcard.controller;

import com.kanjiflashcard.kanjiflashcard.model.Kanji;
import com.kanjiflashcard.kanjiflashcard.repository.KanjiRepository;

import jakarta.persistence.criteria.CriteriaBuilder.In;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.data.domain.PageRequest;

@RestController
@RequestMapping("/api")
public class KanjiController {

    private final KanjiRepository kanjiRepo;

    public KanjiController(KanjiRepository kanjiRepository) {
        this.kanjiRepo = kanjiRepository;
    }

    @GetMapping("/random")
    public Kanji getRandomKanji(@RequestParam(required = false) Integer jlpt) {

        if (jlpt == null) {
            // no filter → return any random kanji
            long count = kanjiRepo.count();
            int index = (int) (Math.random() * count);
            return kanjiRepo.findAll(PageRequest.of(index, 1)).getContent().get(0);
        }

        // filter by jlpt level
        List<Kanji> kanjis = kanjiRepo.findByJlptLevel(jlpt);  

        if (kanjis.isEmpty()) {
            return null; // or throw an exception if preferred
        }
        
        int index = (int) (Math.random() * kanjis.size());
        return kanjis.get(index);
    }

}
