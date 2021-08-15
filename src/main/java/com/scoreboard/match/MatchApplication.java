package com.scoreboard.match;

import com.scoreboard.match.rabitmq.Messaging;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "*")
@EnableBinding(Messaging.class)
public class MatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(MatchApplication.class, args);
    }

}
