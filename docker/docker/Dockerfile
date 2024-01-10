FROM openjdk:17-jdk-slim
EXPOSE 8080
#ARG JAR_FILE_PATH=build/libs/*SNAPSHOP.jar
#COPY ["docker-0.0.1-SNAPSHOT.jar", "app.jar"]
#COPY ["../docker-0.0.1-SNAPSHOT.jar", "app.jar"]
COPY ["../build/libs/docker-0.0.1-SNAPSHOT.jar", "app.jar"]
ENTRYPOINT ["java", "-jar", "app.jar"]
