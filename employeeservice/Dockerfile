# Use Maven to build
FROM maven:3.8.5-openjdk-17-slim AS build

# Set working directory
WORKDIR /app

# Copy source code
COPY ./commonservice ./commonservice
COPY ./employeeservice ./employeeservice

# Build commonservice first
WORKDIR /app/commonservice
RUN mvn clean install -DskipTests

# Build employeeservice
WORKDIR /app/employeeservice
RUN mvn clean package -DskipTests

# Use OpenJDK to run
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/employeeservice/target/*.jar employeeservice.jar
EXPOSE 9002
ENTRYPOINT ["java", "-jar", "employeeservice.jar"]