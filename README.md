# CricketScoreBoard
Live Cricket Match Scoreboard is an API which can help you to 

## Build With
1 Spring Boot - 2.5.2\
2 JPA(Java Persistance API)\
3 Lombok - 2.9.2\
4 Swagger\
5 Flyway\
6 Docker\
7 Rabbitmq - 3.1.3\
8 Mysql - 8\
9 Gradle
## System Design




## Getting Started
This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.
## Installation 
Copy and paste these commands inside the docker file.
#### MySql Docker Image
To run mysql docker image
~~~
$ docker run -d --name mysql -e MYSQL_ROOT_PASSWORD=dummypassword -e MYSQL_DATABASE=hello -e MYSQL_USER=hello -e MYSQL_PASSWORD=dummytodos -p 3308:3306 mysql:5.7
~~~

#### Rabbitmq Docker Image
RabbitMQ is a messaging broker - an intermediary for messaging. It gives your applications a common platform to send and receive messages, and your messages a safe place to live until received.

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

## Database Diagram
This is the representation of database table to understand the tables inside the database.  
![](https://github.com/vaibhavmaithani15/CricketScoreBoard/blob/main/src/main/resources/images/Database.png)






