FROM openjdk:17
EXPOSE 8081
#ADD target/tesla-0.0.1.jar tesla-0.0.1.jar
ENTRYPOINT ["java","-jar","/tesla-0.0.1.jar"]
HEALTHCHECK --interval=5s \
            --timeout=5s\
            CMD curl -f http://127.0.0.1:8081 || exit 1
