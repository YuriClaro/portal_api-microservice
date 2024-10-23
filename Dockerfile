FROM openjdk:21-jdk

WORKDIR /app

COPY build/libs/portal_api-0.0.1-SNAPSHOT.jar /app/portal_api.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "portal_api.jar"]
