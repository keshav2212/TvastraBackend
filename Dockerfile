FROM amazoncorretto:17

ARG VERSION=0.0.1-SNAPSHOT
COPY build/libs/demo-${VERSION}.jar app.jar
COPY src/main/resources/application.properties application.properties

EXPOSE 8080

ENTRYPOINT ["/bin/sh", "-c" , "echo 127.0.0.1 $HOSTNAME >> /etc/hosts && java -jar app.jar application.properties"]