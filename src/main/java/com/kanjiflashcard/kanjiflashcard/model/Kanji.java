package com.kanjiflashcard.kanjiflashcard.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * Kanji entity representing a Kanji character with its attributes.
 */
@Entity
public class Kanji {

    /** The kanji character literal. */
    @Id
    @Getter
    @Setter
    private String literal;

    /** The meanings of the kanji. */
    @Getter
    @Setter
    private String meanings;

    /** The on'yomi (Chinese reading) of the kanji. */
    @Getter
    @Setter
    private String onyomi;

    /** The kun'yomi (Japanese reading) of the kanji. */
    @Getter
    @Setter
    private String kunyomi;

    /** The number of strokes in the kanji. */
    @Getter
    @Setter
    private int strokeCount;

    /** The JLPT level of the kanji. */
    @Getter
    @Setter
    private int jlptLevel;

    /** The frequency ranking of the kanji. */
    @Getter
    @Setter
    private int frequency;

    /**
     * Default constructor.
     */
    public Kanji() {
    }

    /**
     * Constructor with all fields.
     *
     * @param literalVal     the kanji character
     * @param meaningsVal    the meanings
     * @param onyomiVal      the on'yomi reading
     * @param kunyomiVal     the kun'yomi reading
     * @param strokeCountVal the stroke count
     * @param jlptLevelVal   the JLPT level
     * @param frequencyVal   the frequency ranking
     */
    public Kanji(final String literalVal,
            final String meaningsVal,
            final String onyomiVal,
            final String kunyomiVal,
            final int strokeCountVal,
            final int jlptLevelVal,
            final int frequencyVal) {
        this.literal = literalVal;
        this.meanings = meaningsVal;
        this.onyomi = onyomiVal;
        this.kunyomi = kunyomiVal;
        this.strokeCount = strokeCountVal;
        this.jlptLevel = jlptLevelVal;
        this.frequency = frequencyVal;
    }

}
