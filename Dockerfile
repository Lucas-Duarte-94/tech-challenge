FROM openjdk:21-slim

WORKDIR /app

ARG JAR_FILE=target/techChallenge-0.1.0.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]