FROM eclipse-temurin:21-jre-alpine
ARG JAR_FILE=target/system-gym-0.0.1.jar
COPY ${JAR_FILE} app_system_gym.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_system_gym.jar"]