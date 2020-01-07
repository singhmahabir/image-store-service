FROM openjdk:8-jdk-alpine
# author
MAINTAINER Mahabir Singh
# extra metadata
LABEL version="1.0"
LABEL description="First image with Dockerfile."
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]