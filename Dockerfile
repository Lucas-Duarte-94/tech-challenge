# BUILD
FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /app

# Copia os arquivos do projeto para o container
COPY pom.xml .
COPY src ./src

RUN mvn clean package

# RUNTIME
FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar
CMD ["java", "-jar", "app.jar"]