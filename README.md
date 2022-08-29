# WareEyes-SpringBoot-Backend
A web application built on Spring Boot that monitors an inventory system in real-time using Apache Kafka.

This repository contains the backend section. For backend section, refer to https://github.com/ardhan-p/wareeyes-reactjs-frontend

## Spring for Apache Kafka Docs

https://docs.spring.io/spring-kafka/docs/current/reference/html/

## Initialisation Guide
(note: these are the commands to initialise a new Kafka cluster via localhost, it must be done in sequence)

1. Ensure that Java version 11 is installed.

2. Start Kafka zookeeper (on a new terminal shell).

```
bin/zookeeper-server-start.sh config/zookeeper.properties
```

```
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties (for windows)
```

3. Start Kafka server (on a new terminal shell).
```
bin/kafka-server-start.sh config/server.properties
```

```
.\bin\windows\kafka-server-start.bat .\config\server.properties (for windows)
```
4. Download the Java jar file in the releases section.

5. Build and run application with the following command:
```
java -jar app.jar
```

## Application Image

![Screenshot 2022-08-26 at 8 56 00 PM](https://user-images.githubusercontent.com/49318134/187122636-b47ff147-687d-47ae-87a1-49d294488126.png)
