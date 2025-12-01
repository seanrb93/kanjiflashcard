package com.kanjiflashcard.kanjiflashcard.repository;


import com.kanjiflashcard.kanjiflashcard.model.Kanji;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KanjiRepository extends JpaRepository<Kanji, String> {

}
