FROM openjdk:8-jre-alpine
EXPOSE 8080
COPY ./build/libs/docker-stack-rest*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
