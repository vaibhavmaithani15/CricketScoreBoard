# CricketScoreBoard
Live Cricket Match Scoreboard is an API which can help you to 

## Build With
1 Spring Boot\
2 JPA(Java Persistance API)\
3 Lombok\
4 Swagger\
5 Flyway\
6 Docker\
7 Rabbitmq\
8 Mysql\
9 Gradle

## Getting Started
This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.

   ## Dependency Needed
Copy and paste these dependency  into the <b>build.gradle</b> file inside dependency section 
   ##### Spring boot starter
   ~~~
    implementation 'org.springframework.boot:spring-boot-starter'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-validation'
    implementation 'org.springframework.boot:spring-boot-starter-security'
    implementation 'org.springframework:spring-context-support:4.1.8.RELEASE'
   ~~~
   ##### Swagger UI
    implementation 'io.springfox:springfox-swagger2:2.9.2'
    implementation 'io.springfox:springfox-swagger-ui:2.9.2'
    implementation 'org.apache.commons:commons-collections4:4.4'

   ##### Lombok
    compileOnly 'org.projectlombok:lombok'
    annotationProcessor 'org.projectlombok:lombok'

   ##### Spring stream
    implementation 'org.springframework.cloud:spring-cloud-stream:3.1.3'
    implementation 'org.springframework.cloud:spring-cloud-starter-stream-rabbit:3.1.3'
    implementation 'org.springframework.boot:spring-boot-starter-amqp:2.5.3'

   ##### Data base
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'mysql:mysql-connector-java'

  ##### Flyway
    implementation "org.flywaydb:flyway-core"

 ##### Caching
    implementation 'com.google.guava:guava'

 ##### Test
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    implementation 'junit:junit:4.13.1'



## Installation 
Copy and paste these commands inside the scratch.sh file.
#### MySql Docker Image
To install mysql docker image
~~~
$ docker pull mysql
~~~
To run mysql docker image
~~~
$ docker run -d --name mysql -e MYSQL_ROOT_PASSWORD=dummypassword -e MYSQL_DATABASE=hello -e MYSQL_USER=hello -e MYSQL_PASSWORD=dummytodos -p 3308:3306 mysql:5.7
~~~


#### Rabbitmq Docker Image
RabbitMQ is a messaging broker - an intermediary for messaging. It gives your applications a common platform to send and receive messages, and your messages a safe place to live until received.
##### To install rabbitmq docker image
~~~
$ docker pull rabbitmq
~~~
##### To run rabbitmq docker image
~~~
$ docker run -d --rm --name rabbitmq -p 15672:15672 -p 5672:5672 rabbitmq:3-management
 ~~~

## PKCS12 Keystore Generation 
Run these command to generate a TLS(Transport Layer Security) certificate self signed for security of transfer data over the network from http to https 
##### To generate PKCS12 security certificate (self signed)
~~~
$ keytool -genkeypair -alias scoreboard -keyalg RSA -keysize 4096 -storetype PKCS12 -keystore scoreboard.p12 -validity 3650 -storepass xyz123
~~~
##### To view PKCS12 security certificate (self signed)

~~~
$ keytool -list -v -keystore scoreboard.p12
~~~

## Application Properties 
These properties needs to be set inside the <b>application.properties</b> file.

##### Database
    spring.datasource.url=jdbc:mysql://localhost:3308/scoreboard
    spring.datasource.username=rahul
    spring.datasource.password=abc123
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.jpa.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
    spring.jpa.hibernate.ddl-auto=none
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.default_schema=match
    spring.jackson.serialization.fail-on-empty-beans=false
##### Flyway
    spring.flyway.enabled=true
    spring.flyway.schemas=scoreboard
##### Logging
    logging.level.root=INFO
    logging.level.org.springframework.security=TRACE
##### SSH
    server.ssl.key-store-type=PKCS12
    server.ssl.key-store=classpath:keystore/scoreboard.p12
    server.ssl.key-store-password=xyz123
    server.ssl.key-alias=scoreboard
##### Rabbitmq
    spring.cloud.stream.bindings.address.destination=ingest.matchscore
    spring.cloud.stream.bindings.address.contentType=application/json
    spring.cloud.stream.bindings.address.group=matchscore

# Database Diagram
 Database.PNG
![](CricketScoreBoard/src/main/resources/images/Database.PNG)
