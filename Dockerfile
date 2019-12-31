FROM openjdk:alpine

VOLUME /tmp

RUN mkdir common

WORKDIR common

COPY /target/task-svc-0.0.1-SNAPSHOT.jar app.jar

CMD [ "sh", "-c", "java -jar app.jar" ]
