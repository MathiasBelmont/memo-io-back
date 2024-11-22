FROM openjdk:21-jdk-slim

RUN apt-get update && apt-get install -y maven && apt-get clean

WORKDIR /app

COPY pom.xml ./
RUN mvn dependency:go-offline

COPY src ./src

EXPOSE 8080

CMD ["tail", "-f", "/dev/null"]
# Comente a linha acima e descomente a linha abaixo caso queira que a aplicação seja executada automaticamente
# CMD ["mvn", "spring-boot:run"]
