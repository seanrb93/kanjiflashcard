FROM maven:4.0.0-rc-5-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY . /app
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine AS runtime
WORKDIR /app
COPY --from=build /app/target/kanjiflashcard-0.0.1-SNAPSHOT.jar /app/kanjiflashcard-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/kanjiflashcard-0.0.1-SNAPSHOT.jar"]