#FROM adoptopenjdk/openjdk11:jdk-11.0.4_11-alpine-slim
FROM adoptopenjdk/maven-openjdk11
RUN mkdir /app

ADD . /app
WORKDIR /app
RUN mvn clean install

EXPOSE 8080

ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","target/demo-0.0.1-SNAPSHOT.jar"]

