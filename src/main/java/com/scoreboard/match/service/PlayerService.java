package com.scoreboard.match.service;

import com.scoreboard.match.controller.request.PlayerRequest;
import com.scoreboard.match.controller.request.TeamRequest;
import com.scoreboard.match.entity.PlayerEntity;
import com.scoreboard.match.entity.TeamEntity;
import com.scoreboard.match.exception.*;
import com.scoreboard.match.repository.PlayerRepository;
import com.scoreboard.match.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private PlayerRepository repository;


    public PlayerService(PlayerRepository repository, TeamRepository teamRepository) {
        this.repository = repository;

    }

    //ADD Player service
    public PlayerEntity addPlayer(PlayerRequest request) throws PlayerAlreadyExistException {


        PlayerEntity playerEntity = PlayerEntity.builder()
                .name(request.playerName)
                .dob(request.playerDob)
                .country(request.playerCountry)
                .teamEntity(request.teamEntityId)
                .build();
        try {
            return repository.save(playerEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return playerEntity;

    }


    //GET SINGLE PLAYER SERVICE
    public PlayerEntity getPlayer(int playerId) throws PlayerNotFoundException {
        Optional<PlayerEntity> optionalPlayerEntity = repository.findById(playerId);
        PlayerEntity entity = new PlayerEntity();
        if (optionalPlayerEntity.isPresent()) {
            entity.setName(optionalPlayerEntity.get().getName());
            entity.setPlayerId(optionalPlayerEntity.get().getPlayerId());
            entity.setCountry(optionalPlayerEntity.get().getCountry());
            entity.setDob(optionalPlayerEntity.get().getDob());
            entity.setTeamEntity(optionalPlayerEntity.get().getTeamEntity());
            return entity;
        } else {
            throw new PlayerNotFoundException("Player not exists in our system");
        }
    }


    //GET ALL PLAYER SERVICE
    public List<PlayerEntity> getAllPlayer() throws PlayerNotFoundException {
        List<PlayerEntity> players = repository.findAll();
        if (players.isEmpty()) {
            throw new PlayerNotFoundException("Team not exists in our system");
        } else {
            return players;
        }
    }

//    Get all player with a particular team
    public List<PlayerEntity> getAllPlayerInTeam(String teamName) throws PlayerNotFoundException {
        List<PlayerEntity> players = repository.findByTeamEntity(teamName);
        if(players.isEmpty()){
            throw new PlayerNotFoundException("No player exists in out system for this particular team");
        }else{
            return players;
        }
    }



    //update user service
    public PlayerEntity updatePlayer(int playerId, PlayerRequest request) throws PlayerNotFoundException {
        Optional<PlayerEntity> optionalPlayerEntity = repository.findById(playerId);
        if(optionalPlayerEntity.isPresent()){
            PlayerEntity entity = PlayerEntity.builder()
                    .playerId(playerId)
                    .name(request.playerName)
                    .country(request.playerCountry)
                    .dob(request.playerDob)
                    .teamEntity(request.teamEntityId)
                    .build();

            return repository.save(entity);
        }else{
            throw new PlayerNotFoundException("Player not exists in our system");

        }

    }





    //delete a player service
    public boolean deletePlayer(int playerId) throws PlayerNotFoundException {
        Optional<PlayerEntity> optionalPlayerEntity = repository.findById(playerId);
        if(optionalPlayerEntity.isPresent()){
            repository.deleteById(playerId);
            return true;
        }else{
            throw new PlayerNotFoundException("Team not exists in our system");

        }

    }

}
