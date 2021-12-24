FROM openjdk:8-jdk-alpine
COPY target/atm-machine-0.0.1-SNAPSHOT.jar atm-machine-1.0.0.jar
ENTRYPOINT ["java","-jar","/atm-machine-1.0.0.jar"]