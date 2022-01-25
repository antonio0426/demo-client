
FROM maven:3.5-jdk-8-alpine as build

COPY src /usr/src/demo-client/src
COPY pom.xml /usr/src/demo-client
RUN mvn -f /usr/src/demo-client/pom.xml clean package -DskipTests

FROM openjdk:8-jre-alpine
COPY --from=build /usr/src/demo-client/target/demo-client-0.0.1-SNAPSHOT.jar /usr/demo-client/demo-client-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/usr/demo-client/demo-client-0.0.1-SNAPSHOT.jar"]
