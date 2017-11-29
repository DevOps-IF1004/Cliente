FROM java:8
EXPOSE 8090
VOLUME /dados
USER root
ARG JAR_FILE
ADD ${JAR_FILE} cliente.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/cliente.jar"]
