# Introduction

This is a Spring Cloud Config Server. Service configuration data can be manages via a file system or a git-based repository.

# Getting Started

## Software Needed

* Apache Maven

* Docker

## Building Docker Image

Use maven to build an image.

```bash
mvn clean package docker:build
```

## Running the Docker Image

To start the docker image, change to the base directory and run:

```bash
docker-compose -f docker/common/docker-compose.yml up
```

## For Developers

### Running in IDEA

```bash
mvn spring-boot:run
```


## Acknowledgment

This project is based on Spring Microservices In Action, a book by John Carnell.