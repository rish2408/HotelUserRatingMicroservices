# Spring Boot Microservices Setup Guide

This guide explains how to set up a Spring Boot microservices architecture with various features like Service Registry, Service Discovery, Communication between microservices, API Gateway, Config Server and Client, and Fault Tolerance.

## Step 1: Create Different Services (Spring Boot Applications)
1. Create different Spring Boot applications for each microservice.

## Step 2: Implement Service Registry using Eureka SERVER (Spring Boot Application)
1. Add dependencies such as Cloud Bootstrap and Eureka Server.
2. Add @EnableEurekaServer annotation on the main method.
3. Configure application.yml file.

## Step 3: Implement Service Discovery Client (Spring Boot Application Connect with Service Registry)
1. Add dependency Eureka Discovery Client.
2. Add @EnableEurekaClient annotation on the main method.
3. Configure application.yml file.

## Step 4: Microservices Communication (Fetching data from other Spring Boot Application)
1. Use Rest Template in UserService.
2. Remove Host and Port by adding @LoadBalanced on a configuration bean. This allows using the service name instead of the port number.

## Step 5: Using Feign Client instead of Rest Template
1. Add dependency OpenFeign in pom.xml.
2. Add @EnableFeignClients annotation on the main method.
3. Create one service, for example, HotelService interface with @FeignClient annotation and having method definition.

## Step 6: Including Spring Cloud Gateway (API Gateway)
1. Add dependencies such as Cloud Bootstrap, Gateway, Spring Reactive Web, and Eureka Discovery Client.
2. Add @EnableEurekaClient annotation on the main method.
3. Configure application.yml file.

## Step 7: Adding Config Server and Config Client in Microservices
1. Add dependencies such as Config Server and Eureka Discovery Client.
2. Create one repository on Github and add new files (application-dev.yml, application-prod.yml, and application.yml).
3. Add @EnableConfigServer annotation on the main method.
4. Use http://192.168.1.67:8777/application/default to check whether property source data is present or not.
5. Implement Config Client implementation to fetch implementation in other Spring Boot microservices.
6. After adding the dependency to any microservice (Config Client), it will configure as client.
7. Configure application.yml file with profiles active and config import to add different Github profiles to microservice.

## Step 8: Handling Fault Tolerance (By Implementing Circuit Breaker using Resilience4J and Retry using Resilience4J)
1. Add dependencies such as Actuator, AOP, and Resilience4J.
2. Add @CircuitBreaker annotation to the APIs which are calling another services (or another Spring Boot application).
3. Mention fallback method along with Circuit Breaker and add fallback method.
4. Note: Parameters and return type must be the same for the original and fallback method.
5. Configure Actuator and Resilience4J Configuration in application.yml file.

## Step 9: Retry using Resilience4J
1. Add dependencies such as Actuator, AOP, and Resilience4J.
2. Add @Retry annotation to the APIs which are calling another services (or another Spring Boot application).
3. Mention fallback method along with Circuit Breaker and add fallback method.
4. Note: Parameters and return type must be the same for the original and fallback method.
5. Configure Actuator and Retry Configuration in application.yml file.

## Step - 10 Rate Limiter in Microservices

1. Add Dependencies ( Actuator, AOP and Resilience4J)
2. Add @RateLimiter annotation to the API's which are calling another Services( or another spring boot application)
3. Mention fall method along with CircuitBreaker and add Fallback method.
4. Important: Parameters and return type must be the same for the original and fallback method.
5. Configure RateLimiter Configuration in application.yml file
6. Download Apache Jmeter to test performance both on static and dynamic resources.
7. Extract Jmeter and go to bin and select jmeter and run the application.
8. Go to Test Plan and add Thread (Users) as Thread Group and after that add the number of threads (Users)
9. Now go to Thread Group and go to sampler and select Http request after that add SERVER Name or IP: localhost and Port Number (UserService)
10. Add Http Request as (GET) path: (/users/354da100-43c0-468b-9663-c6a3196ef0cd)
11. Now go to HttpRequest on the left-hand side and add Listener as View Result Tree to see the result.
12. Now on the top, start the server and save ViewResult with some name.

## Rate limiting is an important technique for controlling the number of requests that a microservice receives. It helps to prevent overload and keeps the system running smoothly. In this step, we add rate limiting to our microservices using Resilience4J.
## We start by adding the necessary dependencies to our microservice, including Actuator, AOP, and Resilience4J. Then we use the @RateLimiter annotation to limit the number of requests that can be made to our microservice.

## As with the previous steps, we also configure fallback methods to handle situations where the rate limit has been exceeded. We also test the performance of our microservices using Apache Jmeter, a popular tool for load testing.

# Thank-You
