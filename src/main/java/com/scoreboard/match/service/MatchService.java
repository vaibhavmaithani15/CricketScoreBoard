package com.scoreboard.match.service;

import com.scoreboard.match.entity.MatchEntity;
import com.scoreboard.match.entity.PlayerEntity;
import com.scoreboard.match.repository.MatchRepository;
import com.scoreboard.match.repository.PlayerRepository;
import com.scoreboard.match.util.TeamEnum;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable(value = "match-cache")
    public Map<String, List<PlayerEntity>> getDetails(Integer matchId) {
        Optional<MatchEntity> optional = matchRepository.findById(matchId);
        List<PlayerEntity> firstTeamPlayers = new ArrayList<>();
        List<PlayerEntity> secondTeamPlayers = new ArrayList<>();
        optional.ifPresent(match -> {
            firstTeamPlayers.addAll(playerRepository.findByTeamEntity(match.getFirstTeamName().getName()));
            secondTeamPlayers.addAll(playerRepository.findByTeamEntity(match.getSecondTeamName().getName()));
        });
        Map<String, List<PlayerEntity>> teams = new HashMap<>();
        teams.put(TeamEnum.BattingTeam.toString(), firstTeamPlayers);
        teams.put(TeamEnum.BowlingTeam.toString(), secondTeamPlayers);
        return teams;
    }
}
