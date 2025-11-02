FROM eclipse-temurin:21.0.3_9-jdk-alpine as build
COPY ./build/libs/app.jar ./app.jar
ENTRYPOINT ["java","-jar","/app.jar"]