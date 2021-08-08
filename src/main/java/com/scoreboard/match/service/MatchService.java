package com.scoreboard.match.service;

import com.scoreboard.match.controller.request.MatchRequest;
import com.scoreboard.match.controller.request.PlayerRequest;
import com.scoreboard.match.entity.MatchEntity;
import com.scoreboard.match.entity.PlayerEntity;
import com.scoreboard.match.entity.TeamEntity;
import com.scoreboard.match.exception.MatchAlreadyExistException;
import com.scoreboard.match.exception.MatchNotFoundException;
import com.scoreboard.match.exception.PlayerNotFoundException;
import com.scoreboard.match.repository.MatchRepository;
import com.scoreboard.match.repository.PlayerRepository;
import com.scoreboard.match.repository.TeamRepository;
import com.scoreboard.match.util.TeamEnum;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MatchService {
    private MatchRepository matchRepository;
    private PlayerRepository playerRepository;
    private TeamRepository teamRepository;

    public MatchService(MatchRepository matchRepository, PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.matchRepository = matchRepository;
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
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

    //ADD Match service
    public MatchEntity addMatch(MatchRequest request) throws MatchAlreadyExistException {
        Optional<TeamEntity> firstTeamEntity=teamRepository.findById(request.firstTeamName);
        Optional<TeamEntity> secondTeamEntity=teamRepository.findById(request.secondTeamName);
        Optional<MatchEntity> entity = matchRepository.findById(request.matchId);
        MatchEntity matchEntity = null;
        if (firstTeamEntity.isPresent() && secondTeamEntity.isPresent()) {
            matchEntity= MatchEntity.builder()
                    .firstTeamName(firstTeamEntity.get())
                    .secondTeamName(secondTeamEntity.get())
                    .city(request.city)
                    .result(request.result)
                    .country(request.country)
                    .matchDate(request.matchDate)
                    .stadium(request.stadium)
                    .umpire(request.umpire)
                    .build();
            try {
                return matchRepository.save(matchEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return matchEntity;

    }


    //GET ALL Match SERVICE
    public List<MatchEntity> getAllMatches() throws MatchNotFoundException {
        List<MatchEntity> matches = matchRepository.findAll();
        if (matches.isEmpty()) {
            throw new MatchNotFoundException("Match not exists in our system");
        } else {
            return matches;
        }
    }

    //GET SINGLE Match SERVICE
    public MatchEntity getMatch(int matchId) throws MatchNotFoundException {
        Optional<MatchEntity> optionalMatchEntity = matchRepository.findById(matchId);
        MatchEntity entity = new MatchEntity();
        if (optionalMatchEntity.isPresent()) {
            entity.setFirstTeamName(optionalMatchEntity.get().getFirstTeamName());
            entity.setSecondTeamName(optionalMatchEntity.get().getSecondTeamName());
            entity.setMatchId(optionalMatchEntity.get().getMatchId());
            entity.setResult(optionalMatchEntity.get().getResult());
            entity.setCity(optionalMatchEntity.get().getCity());
            entity.setStadium(optionalMatchEntity.get().getStadium());
            entity.setCountry(optionalMatchEntity.get().getCountry());
            entity.setUmpire(optionalMatchEntity.get().getUmpire());
            entity.setMatchDate(optionalMatchEntity.get().getMatchDate());
            return entity;
        } else {
            throw new MatchNotFoundException("Match not exists in our system");
        }
    }


    //update match service
    public MatchEntity updateMatch(int matchId, MatchRequest request) throws MatchNotFoundException {
        Optional<MatchEntity> optionalMatchEntity = matchRepository.findById(matchId);
        Optional<TeamEntity> firstTeamEntity=teamRepository.findById(request.firstTeamName);
        Optional<TeamEntity> secondTeamEntity=teamRepository.findById(request.secondTeamName);
        MatchEntity entity=null;
        if (optionalMatchEntity.isPresent()) {
            entity = MatchEntity.builder()
                    .matchId(optionalMatchEntity.get().getMatchId())
                    .firstTeamName(firstTeamEntity.get())
                    .secondTeamName(secondTeamEntity.get())
                    .city(request.city)
                    .result(request.result)
                    .country(request.country)
                    .matchDate(request.matchDate)
                    .stadium(request.stadium)
                    .umpire(request.umpire)
                    .build();

            try {
                matchRepository.save(entity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return entity;
    }

    //delete a player service
    public boolean deleteMatch(int matchId) throws MatchNotFoundException {
        Optional<MatchEntity> optionalMatchEntity = matchRepository.findById(matchId);
        if (optionalMatchEntity.isPresent()) {
            matchRepository.deleteById(matchId);
            return true;
        } else {
            throw new MatchNotFoundException("Match not exists in our system");

        }

    }

}
