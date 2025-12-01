package com.kanjiflashcard.kanjiflashcard.model;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;

@Entity
public class Kanji {
    @Id
    private String character;
    private String meaning;
    private String onyomi;
    private String kunyomi;
    private int strokeCount;
}

