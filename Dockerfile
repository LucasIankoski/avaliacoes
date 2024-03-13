# Stage 1: Build Stage
FROM ubuntu:latest AS build

# Instalação do OpenJDK 17 e Maven
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven

# Copia o código-fonte e executa o bootJar
COPY . /app
WORKDIR /app
RUN ./gradlew clean build

# Stage 2: Runtime Stage
FROM openjdk:17-slim

EXPOSE 8081

WORKDIR /app

# Copia o JAR construído do estágio anterior
COPY --from=build /app/build/libs/avaliacoes-1.0.0.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
