FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ./target/sell-license-plates-2-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]