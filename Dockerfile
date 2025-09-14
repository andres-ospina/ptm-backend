FROM openjdk:21-jdk-slim
VOLUME /tmp
ARG JAVA_FILE=target/crudapp-0.0.1-SNAPSHOT.jar
COPY ${JAVA_FILE} app.jar
EXPOSE 5002
ENTRYPOINT ["java", "-jar", "app.jar"]
