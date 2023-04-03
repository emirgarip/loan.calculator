FROM adoptopenjdk/openjdk11:latest
MAINTAINER emirgarip
COPY target/loan.calculator-0.0.1-SNAPSHOT.jar loan-calculator-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/loan-calculator-0.0.1-SNAPSHOT.jar"]