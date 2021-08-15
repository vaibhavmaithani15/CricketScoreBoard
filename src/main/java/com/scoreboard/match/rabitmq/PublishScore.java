package com.scoreboard.match.rabitmq;

import com.scoreboard.match.controller.request.ScoreRequest;
import com.scoreboard.match.entity.PlayerEntity;
import com.scoreboard.match.rabitmq.message.Score;
import com.scoreboard.match.service.MatchService;
import com.scoreboard.match.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class PublishScore {

    private ScoreMessaging scoreMessaging;

    private MatchService matchService;

    public PublishScore(ScoreMessaging scoreMessaging, MatchService matchService) {
        this.scoreMessaging = scoreMessaging;
        this.matchService = matchService;
    }

    public void publishToRabbitMq(ScoreRequest request) {
        log.info("Request {}", request.toString());
        Map<String, List<PlayerEntity>> matchDetails = matchService.getDetails(request.matchId);
        Score score=convert(request, matchDetails);
        Message<Score> scoreMessage = MessageBuilder.withPayload(score).build();
        scoreMessaging.getScoreChannel().send(scoreMessage);
    }

    private Score convert(ScoreRequest request, Map<String, List<PlayerEntity>> matchDetails) {
        Score.builder()
                .bating()
                .bowling()
                .build()
    }
}
