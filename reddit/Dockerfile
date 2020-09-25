FROM openjdk:8-jdk-alpine
ADD target/*.jar reddit.jar
ENTRYPOINT ["sh", "-c", "java -jar /reddit.jar"]