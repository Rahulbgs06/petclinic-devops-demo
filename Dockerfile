# Use Eclipse Temurin JDK 17 (Ubuntu-based, works on M1/M2)
FROM eclipse-temurin:17-jdk-focal

# Copy the JAR
ARG JAR_FILE=build/libs/spring-petclinic-4.0.0-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# Run the app
ENTRYPOINT ["java","-jar","/app.jar"]

