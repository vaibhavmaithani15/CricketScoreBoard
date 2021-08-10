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
   #####Spring boot starter
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
![](CricketScoreBoard/blob/main/src/main/resources/images/Database.png)

# Database Commands
Copy paste these sql command inside <b>resources/db/migration/mysql/filename.sql</b> to create the following tables into the database 
##### user table
    CREATE TABLE user (
                      user_name varchar(100) PRIMARY KEY,
                      password VARCHAR(100) NOT NULL,
                      first_name varchar(50),
                      last_name varchar(50),
                      role varchar(20),
                      enabled INT NOT NULL DEFAULT 1,
                      user_created_by varchar(50)
    );
    INSERT INTO user values ('admin','$2a$04$mSlIDuZEJRSAV98WiUeoSOtKpVIcpqw5X9eOAlhtNsTpSskYdpivW','rahul','maithani','admin',1,'System');
    INSERT INTO user values ('rahul','$2a$12$70N7pPSUswUgsZ3ZsA57.ON0eJV/w5SiQnfzn5x./QEMMgDIobXmC','rahul','maithani','admin',1,'System');
##### team table
    CREATE TABLE IF NOT EXISTS `scoreboard`.`team`
    (
    name     VARCHAR(100)  NOT NULL PRIMARY KEY,
    description     VARCHAR(200) NULL,
    selector VARCHAR(100)  NULL,
    country  VARCHAR(100)  NOT NULL
    );

    INSERT INTO team
    values ('IND', 'TOP team in ICC', 'ravi', 'INDIA');
    INSERT INTO team    
    values ('ENG', 'SECOND Top team in ICC', 'mark', 'ENGLAND');
    INSERT INTO team
    values ('AUS', 'THIRD TOP team in ICC', 'andrew', 'AUSTRLIA');
    INSERT INTO team
    values ('NEW', 'FOURTH TOP team in ICC', 'sam', 'NEWZLAND');
##### player table
    CREATE TABLE IF NOT EXISTS `scoreboard`.`player` (
                                                     `player_id` INT(11) NOT NULL AUTO_INCREMENT,
                                                     `name` VARCHAR(45) NOT NULL,
                                                     `dob` DATE NOT NULL,
                                                     `country` VARCHAR(45) NOT NULL,
                                                     `team_name` VARCHAR(45),
                                                     PRIMARY KEY (`player_id`),
                                                     FOREIGN KEY (`team_name`)  REFERENCES `scoreboard`.`team`(`name`)
                                                    );


    INSERT INTO player (name, dob, country, team_name) values ('Sachin','1998-03-12','INDIA','IND');
    INSERT INTO player (name, dob, country, team_name) values ('Gautam','1998-03-12','INDIA','IND');
    
##### match table
    CREATE TABLE IF NOT EXISTS `scoreboard`.`cricket_match`
    (
    `match_id`         INT(11) PRIMARY KEY AUTO_INCREMENT,
    `first_team_name`  VARCHAR(10) NULL DEFAULT NULL,
    `second_team_name` VARCHAR(10) NULL DEFAULT NULL,
    `result`           VARCHAR(45) NOT NULL,
    `match_date`       DATE        NOT NULL,
    `umpire`           VARCHAR(45) NOT NULL,
    `country`          VARCHAR(45) NOT NULL,
    `city`             VARCHAR(45) NOT NULL,
    `stadium`          VARCHAR(45) NOT NULL,
    CONSTRAINT `fk_first_team_name`
        FOREIGN KEY (`first_team_name`)
            REFERENCES `scoreboard`.`team` (`name`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    FOREIGN KEY (`second_team_name`)
        REFERENCES `scoreboard`.`team` (`name`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
    );


    INSERT INTO cricket_match (first_team_name, second_team_name, result, match_date, umpire, country, city, stadium)
    values ('IND', 'AUS', 'NOT STARTED', '2021-09-12', 'Rahul', 'INDIA', 'DELHI', 'FIROSHA KOTLA');

##### out_type table
    CREATE TABLE IF NOT EXISTS `scoreboard`.`out_type`
    (
    `type_id` INT(11)     NOT NULL,
    `type`    VARCHAR(45) NOT NULL,
    PRIMARY KEY (`type_id`)
    );
    INSERT INTO out_type values (1,'BOLD');
    INSERT INTO out_type values (2,'RUNOUT');
    INSERT INTO out_type values (3,'CATCHOUT');
    INSERT INTO out_type values (4,'LBWOUT');
    INSERT INTO out_type values (5,'HITWICKET');
    INSERT INTO out_type values (6,'STUMPOUT');
    INSERT INTO out_type values (7,'RETIREDHURT');
    
##### wicket table 
    CREATE TABLE IF NOT EXISTS `scoreboard`.`wicket`
    (
    `wicket_id`  INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `baller_id`  INT(11) NOT NULL,
    `batsman_id` INT(11) NOT NULL,
    `catch_by`   INT(11) NULL,
    `runout_by`  INT(11) NULL,
    `stump_by`   INT(11) NULL,
    `match_id`   INT(11) NOT NULL,
    CONSTRAINT `wicket_constraint`
        FOREIGN KEY (`baller_id`)
            REFERENCES `scoreboard`.`player` (`player_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    FOREIGN KEY (`batsman_id`)
        REFERENCES `scoreboard`.`player` (`player_id`),
    FOREIGN KEY (`runout_by`)
        REFERENCES `scoreboard`.`player` (`player_id`),
    FOREIGN KEY (`stump_by`)
        REFERENCES `scoreboard`.`player` (`player_id`),
    FOREIGN KEY (`match_id`)
        REFERENCES `scoreboard`.`cricket_match` (`match_id`));
        
##### baller_score table
    CREATE TABLE IF NOT EXISTS `scoreboard`.`baller_score`
    (
    `baller_id`    INT(11) NOT NULL,
    `match_id`     INT(11) NOT NULL,
    `no_of_overs`  INT(11) NULL,
    `no_of_sixes`  INT(11) NULL,
    `no_of_fours`  INT(11) NULL,
    `no_of_threes` INT(11) NULL,
    `no_of_twos`   INT(11) NULL,
    `no_of_ones`   INT(11) NULL,
    `median_over`   INT(11) NULL,
    `missed_ball`  INT(11) NULL,
    `white_ball`   INT(11) NULL,
    `no_ball`      INT(11) NULL,
    `bouncer_ball` INT(11) NULL,
    `wicket_id`    INT(10) NULL,

    PRIMARY KEY (`baller_id`, `match_id`),
    CONSTRAINT `baller_constraint`
        FOREIGN KEY (`match_id`)
            REFERENCES `scoreboard`.`cricket_match` (`match_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    FOREIGN KEY (`baller_id`)
        REFERENCES `scoreboard`.`player` (`player_id`)
    
    );
##### batsman_score table
    CREATE TABLE IF NOT EXISTS `scoreboard`.`batsman_score` (
      `batsman_id` INT(11) NOT NULL,
      `match_id` INT(11) NOT NULL,
      `no_of_sixes` INT(11) NULL,
      `no_of_fours` INT(11) NULL,
      `no_of_threes` INT(11)  NULL,
      `no_of_twos` INT(11)  NULL,
      `no_of_ones` INT(11)  NULL,
      `no_of_zero` INT(11)  NULL,
      `wicket_by` INT(10) NULL,
      `reason_of_out` INT(11) NULL,
      PRIMARY KEY (`batsman_id`,`match_id`),
       CONSTRAINT `batsman_constraint`
            FOREIGN KEY (`match_id`)
                REFERENCES `scoreboard`.`cricket_match` (`match_id`)
                ON DELETE CASCADE
                ON UPDATE CASCADE,
        FOREIGN KEY (`batsman_id`)
            REFERENCES `scoreboard`.`player` (`player_id`)
    --         FOREIGN KEY (`reason_of_out`)
    --         REFERENCES `scoreboard`.`out_type` (`type_id`)
            );





