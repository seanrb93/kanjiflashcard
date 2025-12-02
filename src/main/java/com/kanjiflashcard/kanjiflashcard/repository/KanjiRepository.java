package com.kanjiflashcard.kanjiflashcard.repository;


import com.kanjiflashcard.kanjiflashcard.model.Kanji;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface KanjiRepository extends JpaRepository<Kanji, String> {

    List<Kanji> findByJlptLevel(Integer jlptLevel);

}
