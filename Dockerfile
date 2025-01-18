# Use a lightweight base image for Java 21
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/fyhoj-leaderboard.jar app.jar

# Set environment variable to use the production profile
ENV JAVA_OPTS="-Dspring.profiles.active=prod"

# Expose the port that your application will listen on
EXPOSE 8080

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
