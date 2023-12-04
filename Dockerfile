FROM openjdk:11
WORKDIR /app
COPY target/testTask-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar","testTask-0.0.1-SNAPSHOT.jar"]