FROM openjdk:11
EXPOSE 8085
ADD ./target/kafka-message-service-0.0.1-SNAPSHOT.jar kafka-message-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","kafka-message-service-0.0.1-SNAPSHOT.jar"]