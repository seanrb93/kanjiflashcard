package com.kanjiflashcard.kanjiflashcard.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

// Kanji entity representing a Kanji character with its attributes
@Entity
public class Kanji {

    @Id
    @Getter @Setter
    private String literal;

    @Getter @Setter
    private String meanings;

    @Getter @Setter
    private String onyomi;

    @Getter @Setter
    private String kunyomi;

    @Getter @Setter
    private int strokeCount;

    @Getter @Setter
    private int jlptLevel;

    @Getter @Setter
    private int frequency;
    
    public Kanji() {
    }
    
    // Constructor with all fields
    public Kanji(String literal, String meanings, String onyomi, String kunyomi, int strokeCount, int jlptLevel, int frequency) {
        this.literal = literal;
        this.meanings = meanings;
        this.onyomi = onyomi;
        this.kunyomi = kunyomi;
        this.strokeCount = strokeCount;
        this.jlptLevel = jlptLevel;
        this.frequency = frequency;
    }
    
}

