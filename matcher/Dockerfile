FROM openjdk:15-alpine

ADD build/libs/matcher-0.0.1-SNAPSHOT.jar matcherServer.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "matcherServer.jar"]