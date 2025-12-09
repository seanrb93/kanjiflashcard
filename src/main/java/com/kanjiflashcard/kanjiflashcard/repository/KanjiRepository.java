package com.kanjiflashcard.kanjiflashcard.repository;

import com.kanjiflashcard.kanjiflashcard.model.Kanji;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repository interface for Kanji entity data access.
 */
public interface KanjiRepository extends JpaRepository<Kanji, String> {

    /**
     * Finds all Kanji by JLPT level.
     *
     * @param jlptLevel the JLPT level to filter by
     * @return list of Kanji matching the JLPT level
     */
    List<Kanji> findByJlptLevel(Integer jlptLevel);

}
