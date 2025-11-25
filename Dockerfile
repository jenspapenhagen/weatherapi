# --- build stage ---
FROM maven:3.10.1-jdk-25 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -B -DskipTests package

# --- runtime stage ---
FROM eclipse-temurin:25-jre
COPY --from=build /app/target/*.jar /app/app.jar

ENV OTEL_EXPORTER_OTLP_ENDPOINT=http://otel-collector:4317
ENV JAVA_OPTS=""

EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app/app.jar" ]
