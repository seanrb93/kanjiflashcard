package com.kanjiflashcard.kanjiflashcard.model;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;

@Entity
public class Kanji {
    @Id
    private Long id;
    private String character;

}
