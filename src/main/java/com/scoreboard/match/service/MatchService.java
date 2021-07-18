package com.scoreboard.match.service;

import com.scoreboard.match.entity.MatchEntity;
import com.scoreboard.match.entity.PlayerEntity;
import com.scoreboard.match.repository.MatchRepository;
import com.scoreboard.match.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MatchService {
    private MatchRepository matchRepository;
    private PlayerRepository playerRepository;

    public MatchService(MatchRepository matchRepository, PlayerRepository playerRepository) {
        this.matchRepository = matchRepository;
        this.playerRepository = playerRepository;
    }


    public Map<String, List<PlayerEntity>> getDetails(Integer matchId) {
        Optional<MatchEntity> optional = matchRepository.findById(matchId);
        List<PlayerEntity> firstTeamPlayers = new ArrayList<>();
        List<PlayerEntity> secondTeamPlayers = new ArrayList<>();
        optional.ifPresent(match -> {
            firstTeamPlayers.addAll(playerRepository.findByTeamEntity(match.getFirstTeamName().getName()));
            secondTeamPlayers.addAll(playerRepository.findByTeamEntity(match.getSecondTeamName().getName()));
        });
        Map<String, List<PlayerEntity>> teams = new HashMap<>();
        teams.put("firstTeam", firstTeamPlayers);
        teams.put("secondTeam", secondTeamPlayers);
        return teams;
    }
}
