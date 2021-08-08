package com.scoreboard.match.rabitmq;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
public interface ScoreMessaging {
    String SCORE = "matchscore";

    @Output(SCORE)
    MessageChannel getScoreChannel();

}
