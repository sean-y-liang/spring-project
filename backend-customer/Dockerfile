# Stage 1: Build the application
FROM gradle:8.2.1-jdk17 AS build
WORKDIR /app

COPY gradlew ./gradlew
COPY gradle ./gradle
COPY build.gradle settings.gradle ./
RUN chmod +x gradlew
RUN ./gradlew build --no-daemon

COPY src ./src
RUN ./gradlew build -x test --no-daemon

# Stage 2: Run the application
FROM amazoncorretto:17-alpine
WORKDIR /app
COPY --from=build /app/build/libs/backend-customer-0.0.1-SNAPSHOT.jar /app/backend-customer-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/backend-customer-0.0.1-SNAPSHOT.jar"]
