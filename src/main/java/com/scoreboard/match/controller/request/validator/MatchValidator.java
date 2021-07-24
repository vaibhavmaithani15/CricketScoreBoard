package com.scoreboard.match.controller.request.validator;

import com.scoreboard.match.controller.request.ScoreRequest;
import com.scoreboard.match.entity.PlayerEntity;
import com.scoreboard.match.service.MatchService;
import com.scoreboard.match.util.TeamEnum;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class MatchValidator implements ConstraintValidator<ValidMatch, ScoreRequest> {

    private MatchService matchService;

    public MatchValidator(MatchService matchService) {
        this.matchService = matchService;
    }


    @Override
    public boolean isValid(ScoreRequest scoreRequest, ConstraintValidatorContext context) {
        log.info("SCORE REQUEST INTERCEPTED {}", scoreRequest.toString());
        Map<String, List<PlayerEntity>> teams = matchService.getDetails(scoreRequest.matchId);


        List<PlayerEntity> battingTeam = teams.get(TeamEnum.BattingTeam.toString());
        List<PlayerEntity> bowlingTeam = teams.get(TeamEnum.BowlingTeam.toString());
        if (battingTeam != null || bowlingTeam != null) {
            Optional<PlayerEntity> batsman = battingTeam.stream()
                    .filter(playerEntity -> {
                                boolean isPlayerOfBattingTeam = false;
                                if (scoreRequest.getBatsmanId().equals(playerEntity.getPlayerId())) {
                                    isPlayerOfBattingTeam = true;
                                }
                                return isPlayerOfBattingTeam;
                            }

                    ).findAny();

            Optional<PlayerEntity> baller = bowlingTeam.stream()
                    .filter(playerEntity -> {
                                boolean isPlayerOfBowlingTeam = false;
                                if (scoreRequest.getBallerId().equals(playerEntity.getPlayerId())) {
                                    isPlayerOfBowlingTeam = true;
                                }
                                if (scoreRequest.isOut) {
                                    if (scoreRequest.getCatchBy().equals(playerEntity.getPlayerId())) {
                                        return isPlayerOfBowlingTeam = true;
                                    }
                                    if (scoreRequest.getStumpBy().equals(playerEntity.getPlayerId())) {
                                        return isPlayerOfBowlingTeam = true;
                                    }
                                    if (scoreRequest.getRunoutBy().equals(playerEntity.getPlayerId())) {
                                        return isPlayerOfBowlingTeam = true;
                                    }
                                }
                                return isPlayerOfBowlingTeam;
                            }
                    )
                    .findAny();
            if (batsman.isPresent() && baller.isPresent()) {
                return true;
            }
        }
        return false;
    }
}
