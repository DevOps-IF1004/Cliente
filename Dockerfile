FROM java:8
EXPOSE 8090
ADD /target/Cliente.jar Cliente.jar
ENTRYPOINT ["java", "-jar", "Cliente.jar"]