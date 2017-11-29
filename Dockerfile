FROM java:8
EXPOSE 8090
USER root
RUN mvn clean package
RUN test
ADD /target/Cliente.jar Cliente.jar
ENTRYPOINT ["java", "-jar", "Cliente.jar"]
