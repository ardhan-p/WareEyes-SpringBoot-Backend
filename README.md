# WareEyes-SpringBoot-Backend
A web application built on Spring Boot that monitors an inventory system in real-time using Apache Kafka.

This repository contains the backend section.

## Spring for Apache Kafka Docs

https://docs.spring.io/spring-kafka/docs/current/reference/html/

## Initialisation Guide
(note: these are the commands to initialise a new Kafka cluster via localhost, it must be done in sequence)

1. Start Kafka zookeeper (on a new terminal shell)

**bin/zookeeper-server-start.sh config/zookeeper.properties**

**.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties** (for windows)

2. Start Kafka server (on a new terminal shell)

**bin/kafka-server-start.sh config/server.properties**

**.\bin\windows\kafka-server-start.bat .\config\server.properties** (for windows)

3. Clone the repository using IntelliJ IDE 

4. Build and run application

5. Backend application should run and the APIs should be correctly initialised for use