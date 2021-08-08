package com.scoreboard.match.rabitmq;

import com.scoreboard.match.controller.request.ScoreRequest;
import com.scoreboard.match.rabitmq.message.Score;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class PublishScore {

    private ScoreMessaging scoreMessaging;

    public PublishScore(ScoreMessaging scoreMessaging) {
        this.scoreMessaging = scoreMessaging;
    }

    public void publishToRabbitMq(ScoreRequest request) {
        Score score=new Score();
        Message<Score> scoreMessage = MessageBuilder.withPayload(score).build();
        scoreMessaging.getScoreChannel().send(scoreMessage);
    }
}
