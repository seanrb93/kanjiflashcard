package com.kanjiflashcard.kanjiflashcard.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Kanji {

    @Id
    @Getter @Setter
    private String literal;

    @Getter @Setter
    private String meaning;

    @Getter @Setter
    private String onyomi;

    @Getter @Setter
    private String kunyomi;

    @Getter @Setter
    private int strokes;
    
    public Kanji() {
    }
    
    // Constructor with all fields
    public Kanji(String literal, String meaning, String onyomi, String kunyomi, int strokes) {
        this.literal = literal;
        this.meaning = meaning;
        this.onyomi = onyomi;
        this.kunyomi = kunyomi;
        this.strokes = strokes;
    }
    
}

