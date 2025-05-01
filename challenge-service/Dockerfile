FROM gradle:8.5.0-jdk17 AS builder
WORKDIR /app
COPY . .
RUN gradle clean bootJar --no-daemon

FROM openjdk:17-alpine
WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8081

ENV SERVER_PORT=8081

ENTRYPOINT ["java", "-jar", "app.jar"]