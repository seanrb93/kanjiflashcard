# Kanji Flashcard

A small Spring Boot web app for practicing Jōyō kanji (2,136 characters) with interactive flashcards.

Quick start
```bash
git clone https://github.com/seanrb93/kanjiflashcard
cd kanjiflashcard
mvn spring-boot:run
# or with Docker:
# docker build -t kanjiflashcard . && docker run -p 8080:8080 kanjiflashcard
```
Open http://localhost:8080

Features
- Random kanji flashcards with flip animation
- Filter by JLPT level (N1–N5)
- Shows meanings, readings, stroke count

Keyboard shortcuts
- Space: flip card
- → : next card
- 1–5: filter by JLPT
- 0 or R: random (all levels)

Terraform / Cloud
- This repo contains HCL (Terraform) configs to provision infrastructure.
- Supported clouds: AWS, GCP, Azure.
- Example (run from the terraform config directory):
  ```bash
  terraform init
  terraform apply -var="cloud=aws" -var="region=us-east-1"
  ```
- Ensure your cloud credentials are configured (AWS CLI, gcloud, az CLI, or environment vars) before running Terraform.

API
GET /api/random (optional ?jlpt=N)

Tech stack
Java 21 • Spring Boot 4 • H2 • Maven • Terraform HCL

License
Open source — free to use and modify.
