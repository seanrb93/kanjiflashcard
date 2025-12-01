package com.kanjiflashcard.kanjiflashcard.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Kanji {

    @Id
    @Getter @Setter
    private String character;

    @Getter @Setter
    private String meaning;

    @Getter @Setter
    private String onyomi;

    @Getter @Setter
    private String kunyomi;

    @Getter @Setter
    private int strokeCount;
    
}

