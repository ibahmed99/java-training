FROM openjdk:15-alpine

ADD build/libs/*.jar matcherServer.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "matcherServer.jar"]