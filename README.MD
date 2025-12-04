# Kanji Flashcard

A Spring Boot web application for studying Japanese Kanji using interactive flashcards. Features all 2,136 Jōyō kanji characters with meanings, readings, stroke counts, and JLPT levels.

## Quick Start

```bash
# Clone and run
git clone https://github.com/seanrb93/kanjiflashcard
cd kanjiflashcard
mvn spring-boot:run
```

Open `http://localhost:8080` in your browser.

### Docker

```bash
docker build -t kanjiflashcard .
docker run -p 8080:8080 kanjiflashcard
```

## Features

- Random kanji flashcards with flip animation
- Filter by JLPT level (N1-N5)
- Shows meanings, onyomi/kunyomi readings, stroke count, and frequency

## Keyboard Shortcuts

| Key | Action |
|-----|--------|
| `Space` | Flip card |
| `→` | Next card |
| `1-5` | Filter by JLPT level |
| `0` or `R` | Random (all levels) |

## Tech Stack

Java 21 • Spring Boot 4 • H2 Database • Maven

## API

`GET /api/random` - Returns a random kanji (optional `?jlpt=N` parameter)

## License

Open source - free to use and modify.
