# Stage 1: Build Stage
FROM ubuntu:latest AS build

# Instalação do OpenJDK 17, Maven e Gradle
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven && \
    apt-get install -y wget unzip && \
    wget -q https://services.gradle.org/distributions/gradle-8.2.1-bin.zip && \
    unzip -q gradle-8.2.1-bin.zip -d /opt && \
    rm gradle-8.2.1-bin.zip

# Configuração do ambiente Gradle
ENV GRADLE_HOME /opt/gradle-8.2.1
ENV PATH $PATH:$GRADLE_HOME/bin

# Copia o código-fonte
COPY . /app
WORKDIR /app

# Garante que o script gradlew tem permissões de execução
RUN chmod +x ./gradlew

# Executa o bootJar
RUN ./gradlew clean build

# Stage 2: Runtime Stage
FROM openjdk:17-slim

EXPOSE 8081

WORKDIR /app

# Copia o JAR construído do estágio anterior
COPY --from=build /app/build/libs/avaliacoes-1.0.0.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
