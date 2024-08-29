# Estágio 1: Construir a aplicação
FROM maven:3.8.5-openjdk-17 as build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package

# Estágio 2: Executar a aplicação
FROM openjdk:17-jdk
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar /app.jar
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/demo
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=postgres
ENTRYPOINT ["java","-jar","/app.jar"]
