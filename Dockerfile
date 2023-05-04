# Stage 1: Build with Maven
FROM maven:3.8.3-jdk-11 AS builder
WORKDIR /app
COPY . .
RUN mvn clean -Dmaven.test.skip=true package

# Stage 2: Run the app with JRE
FROM openjdk:11-jre-slim
COPY --from=builder /app/target/tpAchatProject-1.0.jar /app/
WORKDIR /app
CMD ["java", "-jar", "tpAchatProject-1.0.jar"]
