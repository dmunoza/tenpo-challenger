FROM openjdk:11-jre-slim

WORKDIR /app

COPY target/backend-tenpo.jar /app/backend-tenpo.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "backend-tenpo.jar"]