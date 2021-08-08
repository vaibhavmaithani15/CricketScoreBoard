package com.scoreboard.match.config;

import com.scoreboard.match.rabitmq.ScoreMessaging;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding(ScoreMessaging.class)
public class RabbitConfig {
}
