FROM ubuntu:latest

FROM openjdk:11
WORKDIR '/app'
ADD build/libs/match-0.0.1-SNAPSHOT.jar match-0.0.1-SNAPSHOT.jar
EXPOSE 8081
COPY ../../.. .
ENTRYPOINT ["java", "-jar","match-0.0.1-SNAPSHOT.jar"]