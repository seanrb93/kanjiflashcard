package com.kanjiflashcard.kanjiflashcard.controller;

import java.util.List;
import com.kanjiflashcard.kanjiflashcard.model.Kanji;
import com.kanjiflashcard.kanjiflashcard.repository.KanjiRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class KanjiController {

    private final KanjiRepository kanjiRepository;

    public KanjiController(KanjiRepository kanjiRepository) {
        this.kanjiRepository = kanjiRepository;
    }

    @GetMapping("/kanji")
    public List<Kanji> getAllKanji() {
        return kanjiRepository.findAll();
    }

}
