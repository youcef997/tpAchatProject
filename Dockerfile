# Stage 1: Build with Maven
FROM maven:3.8.3-jdk-11 
WORKDIR /app
COPY . .
RUN mvn clean package -Dmaven.test.skip=true

# Stage 2: Run the app with JRE
FROM openjdk:11-jre-slim
COPY /app/target/tpAchatProject-1.0.jar /app/tpAchatProject-1.0.jar
WORKDIR /app
CMD ["java", "-jar", "tpAchatProject-1.0.jar"]

