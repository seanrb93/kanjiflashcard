# Kanji Flashcard

A Spring Boot web application for studying Japanese Kanji characters using flashcards. The app displays random Kanji from the Jōyō kanji (常用漢字) list with their meanings, readings, and other useful information. The front of the card displays the Kanji character, whereas the back of the card displays its meaning and other information.

## Features

- **Random Kanji Display**: Shows a random Kanji character each time you click "Next Kanji"
- **Comprehensive Information**: Each flashcard displays:
  - Kanji character/literal
  - English
  - Onyomi (音読み) - Chinese readings
  - Kunyomi (訓読み) - Japanese readings
  - Stroke count
  - JLPT level
  - Frequency rank
- **Full Jōyō Kanji List**: Contains all 2,136 Jōyō kanji characters
- **Simple and Clean UI**: Minimalist card-based interface for distraction-free learning

## Tech Stack

- **Backend**: Java 21, Spring Boot 4.0.0
- **Database**: H2 (in-memory database)
- **ORM**: Spring Data JPA
- **Frontend**: HTML, CSS, JavaScript (vanilla)
- **Build Tool**: Maven
- **Other**: Lombok

## Prerequisites

- Java 21 or higher
- Maven 3.6+

## Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/seanrb93/kanjiflashcard
   cd kanjiflashcard
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the application**
   
   Open your browser and navigate to: `http://localhost:8080`

## Usage

1. When the application starts, it automatically loads all Jōyō kanji from the CSV data file into the H2 database
2. The main page displays a flashcard with a random Kanji character
3. Click the "Next Kanji" button to load another random Kanji
4. Study the displayed information including meanings, readings, stroke count, JLPT level, and frequency

## Project Structure

```
kanjiflashcard/
├── src/
│   ├── main/
│   │   ├── java/com/kanjiflashcard/kanjiflashcard/
│   │   │   ├── KanjiflashcardApplication.java  # Main application entry point
│   │   │   ├── controller/
│   │   │   │   └── KanjiController.java        # REST API controller
│   │   │   ├── model/
│   │   │   │   └── Kanji.java                  # Kanji entity
│   │   │   ├── repository/
│   │   │   │   └── KanjiRepository.java        # JPA repository
│   │   │   └── util/
│   │   │       └── JoyoCsvLoader.java          # CSV data loader
│   │   └── resources/
│   │       ├── application.properties          # Application configuration
│   │       ├── joyo.csv                        # Jōyō kanji data
│   │       └── static/
│   │           └── index.html                  # Frontend UI
│   └── test/
│       └── java/com/kanjiflashcard/kanjiflashcard/
│           └── KanjiflashcardApplicationTests.java
└── pom.xml                                     # Maven configuration
```

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/random` | Returns a random Kanji object with all its properties |

### Example Response

```json
{
  "literal": "日",
  "meanings": "day|sun|Japan",
  "onyomi": "ニチ|ジツ",
  "kunyomi": "ひ|か",
  "strokeCount": 4,
  "jlptLevel": 5,
  "frequency": 1
}
```

## Data Source

The application uses Jōyō kanji (常用漢字) data, which is the list of 2,136 characters designated by the Japanese Ministry of Education for everyday use. The data includes:

- Kanji character and traditional form (if different)
- Radical
- Stroke count
- Grade level (when taught in Japanese schools)
- English meanings
- Onyomi and Kunyomi readings
- Newspaper frequency rank
- JLPT level

## Keyboard Shortcuts

You can use keyboard shortcuts for quick access:

- 1 (N1 Kanji)
- 2 (N2 Kanji)
- 3 (N3 Kanji)
- 4 (N4 Kanji)
- 5 (N5 Kanji)
- Spacebar (Flip card)
- Right Arrow (Next card)
- 0 or R (Random card from all JLPT levels)

## License

This project is open source. Feel free to use and modify it for your own learning purposes.

## Contributing

Contributions are welcome! Feel free to submit issues and pull requests.
