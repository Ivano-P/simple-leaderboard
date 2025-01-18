# Use the official Eclipse Temurin image for Java 21
FROM openjdk:21

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/fyhoj-leaderboard.jar app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Set the entry point to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
