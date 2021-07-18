package com.scoreboard.match.controller.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scoreboard.match.controller.request.ScoreRequest;

import com.scoreboard.match.entity.PlayerEntity;
import com.scoreboard.match.exception.PlayerNotFoundInTeamException;
import com.scoreboard.match.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Slf4j
public class ScoreRequestInterceptor implements HandlerInterceptor {
    private MatchService matchService;

    public ScoreRequestInterceptor(MatchService matchService) {
        this.matchService = matchService;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream=null;
        ScoreRequest scoreRequest;
        try {
            inputStream=request.getInputStream();
            scoreRequest = objectMapper.readValue(inputStream, ScoreRequest.class);

            log.info("SCORE REQUEST INTERCEPTED {}", scoreRequest.toString());
            Map<String, List<PlayerEntity>> teams = matchService.getDetails(scoreRequest.matchId);


            List<PlayerEntity> battingTeam = teams.get("firstTeam");
            List<PlayerEntity> bowlingTeam = teams.get("secondTeam");
            Optional<PlayerEntity> batsman = battingTeam.stream()
                    .filter(playerEntity -> {
                                boolean isPlayerOfBattingTeam = false;
                                if (scoreRequest.getBatsmanId().equals(playerEntity.getPlayerId())) {
                                    isPlayerOfBattingTeam = true;
                                }
                                return isPlayerOfBattingTeam;
                            }

                    ).findAny();
            if (!batsman.isPresent()) {
                throw new PlayerNotFoundInTeamException("Batsman not in the playing team");
            }
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
            if (!baller.isPresent()) {
                throw new PlayerNotFoundInTeamException("Baller not in the playing team");
            }
        } finally {
            if (inputStream != null) {
//                inputStream.close();
            }
        }
        return true;
    }
}
