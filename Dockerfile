# Stage 1: Build the application
FROM gradle:8.2.1-jdk17 AS build
WORKDIR /app

# Copy only Gradle files to leverage caching
COPY gradlew ./gradlew
COPY gradle ./gradle
COPY build.gradle settings.gradle ./
RUN chmod +x gradlew
RUN ./gradlew build --no-daemon

# Copy the source code and build the application
COPY src ./src
RUN ./gradlew build -x test --no-daemon

# Stage 2: Run the application
FROM amazoncorretto:17-alpine
WORKDIR /app
COPY --from=build /app/build/libs/customer-management-0.0.1-SNAPSHOT.jar /app/customer-management-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/customer-management-0.0.1-SNAPSHOT.jar"]
