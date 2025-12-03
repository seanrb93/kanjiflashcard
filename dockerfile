FROM maven:4.0.0-rc-5-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY . /app
RUN mvn clean package -DskipTests

FROM gcr.io/distroless/java21-debian12 AS runtime
WORKDIR /app
COPY --from=build /app/target/kanjiflashcard-1.0.0.jar /app/kanjiflashcard-1.0.0.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/kanjiflashcard-1.0.0.jar"]