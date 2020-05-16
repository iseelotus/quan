FROM openjdk:8-jdk-alpine
RUN apk update && apk upgrade && apk add netcat-openbsd
ARG JAR_FILE=target/*.jar
RUN mkdir -p /usr/local/eureka-server
ADD ${JAR_FILE} /usr/local/eureka-server/
ADD /docker/run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh
