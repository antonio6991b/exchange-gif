FROM openjdk:8-jdk
COPY exchange-gif-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "exchange-gif-0.0.1-SNAPSHOT.jar"]
