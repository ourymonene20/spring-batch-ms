FROM openjdk:8
VOLUME /tmp
ADD target/batch-ms*.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]