# Stage 1: Build Stage
FROM ubuntu:latest AS build

# Instalação do OpenJDK 17, Maven e Gradle
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven && \
    apt-get install -y wget unzip && \
    wget -q https://services.gradle.org/distributions/gradle-8.2.1-bin.zip && \
    unzip -q gradle-8.2.1-bin.zip -d /opt && \
    rm gradle-8.2.1-bin.zip

# Adiciona o Gradle ao PATH
ENV PATH="/opt/gradle-8.2.1/bin:${PATH}"

# Copia o código-fonte e executa o bootJar
COPY . /app
WORKDIR /app
RUN ./gradlew bootJar
# Stage 2: Runtime Stage
FROM openjdk:17-slim

EXPOSE 8081

WORKDIR /app

# Copia o JAR construído do estágio anterior
COPY --from=build /app/build/libs/avaliacoes-1.0.0.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
