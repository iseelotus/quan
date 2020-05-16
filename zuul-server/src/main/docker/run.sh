#!/bin/sh

echo "********************************************************"
echo "Waiting for the eureka server to start on port $EUREKA_SERVER_PORT"
echo "********************************************************"
while ! `nc -z eureka_server  $EUREKA_SERVER_PORT`; do sleep 3; done
echo "******* Eureka Server has started"

echo "********************************************************"
echo "Starting Zuul Service"
echo "********************************************************"
java -Dserver.port=$SERVER_PORT   \
     -Deureka.client.serviceUrl.defaultZone=$EUREKA_SERVER_URI   \
     -Dspring.profiles.active=$PROFILE                          \
     -jar /usr/local/zuul-service/@project.build.finalName@.jar
