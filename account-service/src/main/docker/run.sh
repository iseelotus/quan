#!/bin/sh

echo "********************************************************"
echo "Waiting for the eureka server to start on port $EUREKA_SERVER_PORT"
echo "********************************************************"
while ! `nc -z eureka_server $EUREKA_SERVER_PORT`; do sleep 3; done
echo "******* Eureka Server has started"


echo "********************************************************"
echo "Waiting for the database server to start on port $DATABASE_SERVER_PORT"
echo "********************************************************"
while ! `nc -z database $DATABASE_SERVER_PORT`; do sleep 3; done
echo "******** Database Server has started "

echo "********************************************************"
echo "Waiting for the kafka server to start on port $KAFKA_SERVER_PORT"
echo "********************************************************"
while ! `nc -z kafka $KAFKA_SERVER_PORT`; do sleep 10; done
echo "******* Kafka Server has started"

echo "********************************************************"
echo "Waiting for the kafka server to start on port $ZOOKEEPER_SERVER_PORT"
echo "********************************************************"
while ! `nc -z zookeeper $ZOOKEEPER_SERVER_PORT`; do sleep 10; done
echo "******* Zookeeper has started"

echo "********************************************************"
echo "Waiting for the REDIS server to start  on port $REDIS_PORT"
echo "********************************************************"
while ! `nc -z redis $REDIS_PORT`; do sleep 10; done
echo "******* REDIS has started"


echo "********************************************************"
echo "Starting Account Server"
echo "********************************************************"
java -Dserver.port=$SERVER_PORT   \
     -Deureka.client.serviceUrl.defaultZone=$EUREKA_SERVER_URI             \
     -Dspring.cloud.stream.kafka.binder.zkNodes=$KAFKA_SERVER_URI          \
     -Dspring.cloud.stream.kafka.binder.brokers=$ZOOKEEPER_SERVER_URI             \
     -Dspring.profiles.active=$PROFILE -jar /usr/local/account-service/@project.build.finalName@.jar
