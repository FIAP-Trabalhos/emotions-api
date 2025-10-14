# Projeto - emotions-api

##  Como executar localmente com Docker

1. Começar executando o build na raiz:
docker build -t emotions-api:latest .

2. Executar o container (sem MongoDB):
docker run -d -p 8080:8080 --name emotions_app emotions-api:latest

2.5. Executar o container compose:
docker-compose up -d

3. Se quiser derrubar o container:
docker-compose down

##  Pipeline CI/CD

Usamos o github actions para controlar nosso workflow, criando os jobs no repositorio, e conectamos eles com o Azure Web Apps.

##  Containerização

FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENV SPRING_APPLICATION_NAME=emotions
ENV SERVER_PORT=8080
ENV SPRING_DATA_MONGODB_URI=mongodb+srv://victoraugustogfavaro:gnEbWas9B31jIGQk@cluster.mybopzg.mongodb.net/challenge
ENV API_KEY=981518ff-ee69-4444-a8de-9f1fc60bfe39
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

##  Prints do funcionamento

Prints serão disponibilizadas no pdf.

##  Tecnologias utilizadas

Java Spring, MongoDB, Github Actions, Docker e Azure Web App.
