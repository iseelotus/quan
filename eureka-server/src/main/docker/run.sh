#!/bin/sh
echo "********************************************************"
echo "Starting the Eureka Server"
echo "********************************************************"
java -jar /usr/local/eureka-server/@project.build.finalName@.jar
