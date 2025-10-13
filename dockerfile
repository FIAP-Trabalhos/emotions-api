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
