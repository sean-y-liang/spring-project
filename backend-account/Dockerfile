# Stage 1: Build the application
FROM gradle:8.2.1-jdk17 AS build
WORKDIR /app

# Copy Gradle wrapper and configuration files
COPY gradlew ./gradlew
COPY gradle ./gradle
COPY build.gradle settings.gradle ./
RUN chmod +x gradlew
RUN ./gradlew build --no-daemon

# Copy source code and build the application
COPY src ./src
RUN ./gradlew build -x test --no-daemon

# Stage 2: Run the application
FROM amazoncorretto:17-alpine
WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=build /app/build/libs/backend-account-0.0.1-SNAPSHOT.jar /app/backend-account-0.0.1-SNAPSHOT.jar

# Expose port and define the entry point
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/backend-account-0.0.1-SNAPSHOT.jar"]
