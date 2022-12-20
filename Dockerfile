FROM openjdk:17
EXPOSE 8081
ADD target/tesla-0.0.1.jar tesla-0.0.1.jar
ENTRYPOINT ["java","-jar","/tesla-0.0.1.jar"]