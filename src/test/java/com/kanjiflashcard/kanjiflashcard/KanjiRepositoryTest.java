package com.kanjiflashcard.kanjiflashcard;

import com.kanjiflashcard.kanjiflashcard.model.Kanji;
import com.kanjiflashcard.kanjiflashcard.repository.KanjiRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class KanjiRepositoryTest {

    @Autowired
    private KanjiRepository repo;

    @Test
    public void testFindByJlpt() {
        List<Kanji> n5 = repo.findByJlptLevel(5);

        assertThat(n5)
            .as("Should find some N5 kanji")
            .isNotEmpty();

        boolean containsN5 = n5.stream().anyMatch(k -> k.getJlptLevel() == 5);
        assertThat(containsN5).isTrue();
    }
}