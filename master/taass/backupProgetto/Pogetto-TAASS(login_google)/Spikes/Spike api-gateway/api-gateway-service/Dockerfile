# Use an official Maven runtime as a parent image
FROM maven:latest AS build

# Set the working directory in the container
WORKDIR /app

# Copy the project files into the container
COPY . .

# Build the Spring Boot application using Maven
RUN mvn clean package -DskipTests

# Create a new image for the application
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the Maven build stage
COPY --from=build /app/target/api-gateway-service-0.0.1-SNAPSHOT.jar api-gateway-service-1.0.0.jar

# Expose the port your Spring Boot app listens on (default is 8080)
EXPOSE 8080

# Define the command to run your Spring Boot application
CMD ["java", "-jar", "api-gateway-service-1.0.0.jar"]