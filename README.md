# EXCHANGE RATES API


Exchange API is a RESTful API providing exchange rates and conversions for many currencies.

## Table of Contents


1. Technologies
1. Running
1. Configuration
1. Documentation
1. Development and Testing


## 1. Technologies

Exchange Rates API is a Spring Boot project written in Java 15 and it uses following:


- Spring Boot (http://start.spring.io)
- Hibernate, Spring Data JPA 
- MAVEN

## 2. Running

1. Install "Docker" https://docs.docker.com/get-docker/

2. PostgreSQL and PGadmin installation: Run following commands in Terminal

```bash
// Go to exchangeratesapi project root directory
$ docker-compose up -d
```

Open following url for monitorin Database `http://localhost:80/`

3. Install Maven with following guide: https://maven.apache.org/install.html

4. Run following commands in Terminal window

```bash
// Go to exchangeratesapi project root directory
$ mvn package
```

5. Run following commands in Terminal window

```bash
$ java -jar target/exchangerates-0.0.1-SNAPSHOT.jar com.atilla.exchangerates.ExchangeratesApplication
```

6. Run following command for testing requirements

```bash
// Test exchange-rate
$ curl -X GET http://localhost:8080/api/exchange-rate/2012-12-23/USD/EUR
$ curl -X GET http://localhost:8080/api/exchange-rate/2020-12-23/EUR/TRY


// Tes history
$ curl -X GET http://localhost:8080/api/exchange-rate/history/daily/2021/03/19
$ curl -X GET http://localhost:8080/api/exchange-rate/history/monthly/2021/03

```
 
By default, Exchange RATES API runs on localhost:8080.
 

## 3. Configuration

Configuration: application.propertis
```properties
# Configurations
spring.application.name=exchangeratesapi
server.port=8080
debug=true
trace=false
logging.level.root=DEBUG
logging.level.org.springframework=DEBUG
logging.level.org.hibernate=DEBUG
logging.path = logs/${spring.application.name}.log
spring.output.ansi.enabled=ALWAYS
 
provider.exchangeratesapi.baseCurrency= EUR
provider.exchangeratesapi.host= https://api.exchangeratesapi.io
provider.exchangeratesapi.accessKey	= 
spring.datasource.url= jdbc:postgresql://localhost:5432/Rates
spring.datasource.username= postgres
spring.datasource.password= 123
spring.jpa.hibernate.ddl-auto= create-drop
spring.jpa.show-sql	= true
spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql= true

springdoc.api-docs.path	=/docs
springdoc.swagger-ui.path=/swagger.html
spring.mvc.throw-exception-if-no-handler-found = true
spring.resources.add-mappings = false

# Error Messages
error.invaliddate = Only dates between 2000-01-01 and yesterday are allowed.
```

There is no need for a manual configuration as default values are enough to run Exchange Rates API  

## 4. Documentation

API documentation is managed by Spring Doc. To access Swagger UI, open /swagger.html in a web browser after running Exchange API. You can also find Open API specification at /docs as a Json.

## 5. Testing

Exchange API is built with Maven. You can use regular Maven tasks such as clean, compile, test tasks for development and testing.
