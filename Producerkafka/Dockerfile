FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/Producer-kafka-1.0-SNAPSHOT.jar /producer-kafka.jar
CMD ["java", "-jar", "/app.jar", "--spring.profiles.active=prod"]
EXPOSE 8080