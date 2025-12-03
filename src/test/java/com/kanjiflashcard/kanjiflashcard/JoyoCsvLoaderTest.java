package com.kanjiflashcard.kanjiflashcard;

import com.kanjiflashcard.kanjiflashcard.model.Kanji;
import com.kanjiflashcard.kanjiflashcard.repository.KanjiRepository;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class JoyoCsvLoaderTest {

    @Autowired
    private KanjiRepository kanjiRepo;

    @Test
    public void testJoyoCsvLoaded() {
        long count = kanjiRepo.count();
        assertThat(count)
        .as("CSV should load at least 1000 Joyo Kanji")
        .isGreaterThan(1000); // There are 2136 Joyo Kanji

        Kanji sample = kanjiRepo.findById("愛").orElse(null);
        assertThat(sample).isNotNull();
        assertThat(sample.getMeanings()).contains("love");
        assertThat(sample.getJlptLevel()).isEqualTo(3);

    }

}