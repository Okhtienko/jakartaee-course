FROM maven:3.8.6-jdk-17 AS builder

COPY pom.xml pom.xml
RUN mvn package

COPY ./src src/
RUN mvn package

FROM tomcat:9.0.53-jdk17-corretto

COPY --from=builder target/lesson22-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/lesson22.war
