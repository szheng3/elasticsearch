#FROM adoptopenjdk/openjdk11:jdk-11.0.4_11-alpine-slim
FROM adoptopenjdk/maven-openjdk11
RUN mkdir /app

# copy the project files
COPY ./pom.xml ./pom.xml

# build all dependencies for offline use
RUN mvn dependency:go-offline -B

# copy your other files
COPY ./src ./src

# build for release
RUN mvn package
EXPOSE 8080

ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","target/demo-0.0.1-SNAPSHOT.jar"]

