package com.kanjiflashcard.kanjiflashcard.controller;

import java.util.List;
import com.kanjiflashcard.kanjiflashcard.model.Kanji;
import com.kanjiflashcard.kanjiflashcard.repository.KanjiRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;

@RestController
@RequestMapping("/api")
public class KanjiController {

    private final KanjiRepository kanjiRepo;

    public KanjiController(KanjiRepository kanjiRepository) {
        this.kanjiRepo = kanjiRepository;
    }

    @GetMapping("/kanji")
    public List<Kanji> getAllKanji() {
        return kanjiRepo.findAll();
    }

    @GetMapping("/random")
    public Kanji getRandomKanji() {
        long count = kanjiRepo.count();
        if (count == 0) {
            return null; // Or throw an exception
        }
        int randomIndex = (int) (Math.random() * count);
        return kanjiRepo.findAll(PageRequest.of(randomIndex, 1)).getContent().get(0);        
    }

}
