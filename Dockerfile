FROM openjdk:11-slim
WORKDIR /opt/spring-cloud-playground
COPY target/core-config-server-0.0.1.jar .
ENTRYPOINT ["java","-jar","core-config-server-0.0.1.jar"]