## Spring Cloud Services Used

### Eureka

Service discovery

### Zuul

Services gateway.

- Usage as interceptor: a correlation id is injected into each http request and response

- Usage as router: person service is reacheable under ```http://localhost:5555/api/person-service/``` and account service under ```http://localhost:5555/api/account-service```

### Hystrix

Circuit breaker

### Spring Cloud Config Server

Configs are saved under github repo.

### Kafka

Event-driven: each time the person service changes person data, a message will be added to the message queue. Account service reads from the message queue. It clears the cache each time it reads a UPDATE/DELETE change from the person service.

## Getting Started

Background programs:

- Kafka:  ```zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties & kafka-server-start /usr/local/etc/kafka/server.properties```

- Redis: ```redis-server```

- PostgreSQL: ```pg_ctl -D /usr/local/var/postgres start```

To run the services, use ```mvn spring-boot:run```


## TODO

- docker compose for all services

## Acknowledgment

This project is based on [Spring Microservices In Action](https://www.amazon.com/Spring-Microservices-Action-John-Carnell/dp/1617293989), a brilliant book by John Carnell.