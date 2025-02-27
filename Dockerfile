# Build stage
FROM gradle:7.6.1-jdk17-alpine AS build
WORKDIR /app

# Copy Gradle Wrapper & build script trước để tối ưu cache
COPY gradle gradle
COPY gradlew build.gradle settings.gradle ./
RUN chmod +x gradlew

# Copy source code vào sau cùng
COPY src src

# Build ứng dụng mà không chạy test
RUN ./gradlew build -x test

# Runtime stage
FROM openjdk:17-slim
WORKDIR /app

# Copy file JAR từ build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Mở cổng 8080
EXPOSE 8080

# Chạy ứng dụng
ENTRYPOINT ["java","-jar","app.jar"]

