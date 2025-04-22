# Use Maven to build
FROM maven:3.8.5-openjdk-17-slim AS build

# Set working directory
WORKDIR /app

# Copy source code
COPY ./commonservice ./commonservice
COPY ./borrowingservice ./borrowingservice

# Build commonservice first
WORKDIR /app/commonservice
RUN mvn clean install -DskipTests

# Build borrowingservice
WORKDIR /app/borrowingservice
RUN mvn clean package -DskipTests

# Use OpenJDK to run
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/borrowingservice/target/*.jar borrowingservice.jar
EXPOSE 9004
ENTRYPOINT ["java", "-jar", "borrowingservice.jar"]