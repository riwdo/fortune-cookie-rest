# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.1/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.1/maven-plugin/reference/html/#build-image)
* [Spring Boot Testcontainers support](https://docs.spring.io/spring-boot/docs/3.2.1/reference/html/features.html#features.testing.testcontainers)
* [Testcontainers Kafka Modules Reference Guide](https://java.testcontainers.org/modules/kafka/)
* [Testcontainers Postgres Module Reference Guide](https://java.testcontainers.org/modules/databases/postgres/)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.2.1/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)
* [Testcontainers](https://java.testcontainers.org/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.2.1/reference/htmlsingle/index.html#web)
* [Spring for Apache Kafka](https://docs.spring.io/spring-boot/docs/3.2.1/reference/htmlsingle/index.html#messaging.kafka)

### Guides

The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Testcontainers support

This project
uses [Testcontainers at development time](https://docs.spring.io/spring-boot/docs/3.2.1/reference/html/features.html#features.testing.testcontainers.at-development-time).

Testcontainers has been configured to use the following Docker images:

* [`confluentinc/cp-kafka:latest`](https://hub.docker.com/r/confluentinc/cp-kafka)
* [`postgres:latest`](https://hub.docker.com/_/postgres)

Please review the tags of the used images and set them to the same as you're running in production.

