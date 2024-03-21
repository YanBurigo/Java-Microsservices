FROM openjdk:21-jdk

EXPOSE 8761

WORKDIR /app

COPY target/naming-server-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]