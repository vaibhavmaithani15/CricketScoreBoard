package com.scoreboard.match.rabitmq;

import com.scoreboard.match.controller.request.ScoreRequest;
import com.scoreboard.match.entity.BallerScoreEntity;
import com.scoreboard.match.entity.BatsmanScoreEntity;
import com.scoreboard.match.rabitmq.message.Bating;
import com.scoreboard.match.rabitmq.message.Bowling;
import com.scoreboard.match.rabitmq.message.Score;
import com.scoreboard.match.service.MatchService;
import com.scoreboard.match.service.ScoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class PublishScore {

    private ScoreMessaging scoreMessaging;

    private MatchService matchService;
    private ScoreService scoreService;

    public PublishScore(ScoreMessaging scoreMessaging, MatchService matchService, ScoreService scoreService) {
        this.scoreMessaging = scoreMessaging;
        this.matchService = matchService;
        this.scoreService = scoreService;
    }

    public void publishToRabbitMq(ScoreRequest request) {
        log.info("Request {}", request.toString());
        BatsmanScoreEntity batsmanScoreEntity = scoreService.getBatsmanScoreByMatch(request.matchId, request.batsmanId);
        BallerScoreEntity ballerScoreEntity = scoreService.getBallerSpell(request.matchId, request.ballerId);

        Score score = convert(batsmanScoreEntity, ballerScoreEntity);
        Message<Score> scoreMessage = MessageBuilder.withPayload(score).build();
        scoreMessaging.getScoreChannel().send(scoreMessage);
    }

    private Score convert(BatsmanScoreEntity batsman, BallerScoreEntity baller) {

        List<Bating> batingList = new ArrayList<>();
        List<Bowling> bowlingList = new ArrayList<>();

        batingList.add(
                Bating.builder()
                        .position("Strike")
                        .boundary(batsman.getNoOfFours())
                        .name("Virat Kholi")
                        .ball(3)
                        .run(10)
                        .six(batsman.getNoOfSixes())
                        .sRate(5.5f)
                        .build()
        );
        bowlingList.add(
                Bowling.builder()
                        .name("Malinga")
                        .boundary("4")
                        .econ("4")
                        .maiden("2")
                        .over("4")
                        .position("Current Spell")
                        .six("1")
                        .wicket("2")
                        .build()
        );

        return Score.builder()
                .bating(batingList)
                .bowling(bowlingList)
                .build();
    }
}
