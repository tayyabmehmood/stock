FROM amazoncorretto:11-al2-jdk
COPY target/Stock-0.0.1-SNAPSHOT.jar Stock-0.0.1-SNAPSHOT.jar
COPY target/classes/application.properties application.properties
CMD java -Dspring.config.location=application.properties -jar Stock-0.0.1-SNAPSHOT.jar
