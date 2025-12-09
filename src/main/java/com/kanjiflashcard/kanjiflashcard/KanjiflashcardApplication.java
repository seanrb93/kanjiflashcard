package com.kanjiflashcard.kanjiflashcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the Kanji Flashcard application.
 */
@SpringBootApplication
public final class KanjiflashcardApplication {

     /**
      * Private constructor to prevent instantiation.
      */
     private KanjiflashcardApplication() {
     }

     /**
      * Main entry point for the application.
      *
      * @param args command line arguments
      */
     public static void main(final String[] args) {
          SpringApplication.run(KanjiflashcardApplication.class, args);
     }

}
