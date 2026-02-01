# ---- Build stage ----
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copy pom first for better Docker layer caching
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Download dependencies (cached)
RUN ./mvnw -q -DskipTests dependency:go-offline || mvn -q -DskipTests dependency:go-offline

# Copy source and build
COPY src ./src
RUN ./mvnw -DskipTests clean package || mvn -DskipTests clean package


# ---- Run stage ----
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Render provides PORT; default to 9090 locally
ENV PORT=9090
EXPOSE 9090

ENTRYPOINT ["sh", "-c", "java -Dserver.port=${PORT} -jar app.jar"]
