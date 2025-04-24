FROM openjdk:20-jdk
COPY /target/journalBackend-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
