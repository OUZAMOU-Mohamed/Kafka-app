FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/kafka-spring-0.0.1-SNAPSHOT.jar /consumer-kafka.jar
CMD ["java", "-jar", "/app.jar", "--spring.profiles.active=prod"]
EXPOSE 8083