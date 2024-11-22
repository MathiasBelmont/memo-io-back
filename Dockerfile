FROM openjdk:21-jdk-slim

RUN apt-get update && apt-get install -y maven && apt-get clean

WORKDIR /app

COPY pom.xml ./
RUN mvn dependency:go-offline

COPY src ./src

EXPOSE 8080

CMD ["mvn", "spring-boot:run"]
