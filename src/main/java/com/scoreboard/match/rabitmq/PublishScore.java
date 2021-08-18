package com.scoreboard.match.rabitmq;

import com.scoreboard.match.controller.request.ScoreRequest;
import com.scoreboard.match.entity.BallerScoreEntity;
import com.scoreboard.match.entity.BatsmanScoreEntity;
import com.scoreboard.match.entity.PlayerEntity;
import com.scoreboard.match.rabitmq.message.Bating;
import com.scoreboard.match.rabitmq.message.Bowling;
import com.scoreboard.match.rabitmq.message.Score;
import com.scoreboard.match.repository.PlayerRepository;
import com.scoreboard.match.service.MatchService;
import com.scoreboard.match.service.ScoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class PublishScore {

    private ScoreMessaging scoreMessaging;

    private MatchService matchService;
    private ScoreService scoreService;
    private PlayerRepository playerRepository;

    public PublishScore(ScoreMessaging scoreMessaging, MatchService matchService, ScoreService scoreService, PlayerRepository playerRepository) {
        this.scoreMessaging = scoreMessaging;
        this.matchService = matchService;
        this.scoreService = scoreService;
        this.playerRepository = playerRepository;
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
        Optional<PlayerEntity> batsmanOptional = playerRepository.findById(batsman.getBatsmanId());
        Optional<PlayerEntity> ballerOptional = playerRepository.findById(baller.getBallerId());

        int fourCount=0;
        int sixCount=0;
        int threeCount=0;
        int twoCount=0;
        int oneCount=0;
        int zerosCount=0;
        if(batsman.getNoOfSixes()!=0){
            sixCount++;
        }else if(batsman.getNoOfFours()!=0){
            fourCount++;
        }else if(batsman.getNoOfThrees()!=0){
            threeCount++;
        }else if(batsman.getNoOfTwos()!=0){
            twoCount++;
        }else if(batsman.getNoOfOnes()!=0){
            oneCount++;
        }else if(batsman.getNoOfZero()!=0){
            zerosCount++;
        }
        int totalRuns=(sixCount*6)+(fourCount*4)+(threeCount*3)+(twoCount*2)+(oneCount*1);
        int totalBallsPlayedByPlayer=sixCount+fourCount+threeCount+twoCount+oneCount+zerosCount;

        batingList.add(
                Bating.builder()
                        .position("Strike")
                        .boundary(fourCount)
                        .name(batsmanOptional.get().getName())
                        .ball(totalBallsPlayedByPlayer)
                        .run(totalRuns)
                        .six(sixCount)
                        .sRate(5.5f)
                        .build()
        );
        
        bowlingList.add(
                Bowling.builder()
                        .name(ballerOptional.get().getName())
                        .boundary(baller.getNoOfFours())
                        .econ(1)
                        .maiden(2)
                        .over(4)
                        .position("Current Spell")
                        .six(baller.getNoOfSixes())
                        .wicket(3)
                        .build()
        );

        return Score.builder()
                .bating(batingList)
                .bowling(bowlingList)
                .build();
    }
}
