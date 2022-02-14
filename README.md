# Spring-Microservices
This is a Spring Boot Microservices built with Spring Boot, Spring Cloud, Eureka, API Gateway

# Application                           Port
Limits Microservice                     8080
Spring Cloud Config Server              8888
Currency Exchange Microservice          8000
Currency Conversion Microservice        8100
Netflix Eureka Naming Server            8761
API Gateway                             8765
Zipkin Distributed Tracing Server       9411

URLs
Application	URL
Limits Service	http://localhost:8080/limits http://localhost:8080/actuator/refresh (POST)
Spring Cloud Config Server	http://localhost:8888/limits-service/default http://localhost:8888/limits-service/dev
Currency Converter Service - Direct Call	http://localhost:8100/currency-converter/from/USD/to/INR/quantity/10
Currency Converter Service - Feign	http://localhost:8100/currency-converter-feign/from/EUR/to/INR/quantity/10000
Currency Exchange Service	http://localhost:8000/currency-exchange/from/EUR/to/INR http://localhost:8001/currency-exchange/from/USD/to/INR
Eureka	http://localhost:8761/
Zuul - Currency Exchange & Exchange Services	http://localhost:8765/currency-exchange-service/currency-exchange/from/EUR/to/INR http://localhost:8765/currency-conversion-service/currency-converter-feign/from/USD/to/INR/quantity/10
Zipkin	http://localhost:9411/zipkin/
Spring Cloud Bus Refresh	http://localhost:8080/actuator/bus-refresh (POST)