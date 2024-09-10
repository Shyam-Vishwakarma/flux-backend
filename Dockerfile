# # Use the official Maven image to build the project
# FROM maven:3.8.4-openjdk-17 AS build
#
# # Set the working directory inside the container
# WORKDIR /app
#
# # Copy the pom.xml and download project dependencies
# COPY pom.xml .
# RUN mvn dependency:go-offline
#
# # Copy the project files and build the project
# COPY src ./src
# RUN mvn clean package -DskipTests
#
# # Use the official OpenJDK runtime image
# FROM openjdk:17-jdk-slim
#
# # Set the working directory for the runtime
# WORKDIR /app
#
# # Copy the built JAR file from the Maven build stage
# COPY --from=build /app/target/Flux-0.0.1-SNAPSHOT.jar .
#
# # Expose port 8080 to the outside world
# EXPOSE 8080
#
# # Command to run the application
# ENTRYPOINT ["java", "-jar", "/app/Flux-0.0.1-SNAPSHOT.jar"]

FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn package -DskipTests

# Run stage
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/Flux-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]