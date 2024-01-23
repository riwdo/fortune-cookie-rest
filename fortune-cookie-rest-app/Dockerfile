# Use a base image with Java and an appropriate version
FROM openjdk:21-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY target/fortune-cookie-rest-app-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port your Spring Boot app is running on
EXPOSE 8080

# Command to run your application
CMD ["java", "-jar", "app.jar"]