package com.scoreboard.match.service;

import com.scoreboard.match.controller.request.TeamRequest;
import com.scoreboard.match.entity.TeamEntity;
import com.scoreboard.match.exception.TeamAlreadyExistException;
import com.scoreboard.match.exception.TeamNotFoundException;
import com.scoreboard.match.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    private TeamRepository repository;

    public TeamService(TeamRepository repository) {
        this.repository = repository;
    }

    // ADD TEAM SERVICE
    public TeamEntity addTeam(TeamRequest request) throws TeamAlreadyExistException {
//        System.out.println(request.name);
//        System.out.println(request.desc);
//        System.out.println(request.selector);
//        System.out.println(request.country);

        Optional<TeamEntity> teamName = repository.findById(request.name);
        if(teamName.isPresent()){
            throw new TeamAlreadyExistException("Team already exist in our system please select another team name");
        }else{
            TeamEntity teamEntity = TeamEntity.builder()
                    .name(request.name)
                    .description(request.desc)
                    .selector(request.selector)
                    .country(request.country)
                    .build();
            try {
                return repository.save(teamEntity);
            }catch (Exception e){
                e.printStackTrace();
            }
            return teamEntity;
        }
    }


    //GET ALL TEAM SERVICE
    public List<TeamEntity> getAllTeam() throws TeamNotFoundException {
        List<TeamEntity> teams = repository.findAll();
        if(teams.isEmpty()){
            throw new TeamNotFoundException("Team not exists in our system");
        }else{
            return teams;
        }
    }


    //GET SINGLE TEAM SERVICE
    public TeamEntity getTeam(String teamName) throws TeamNotFoundException {
        Optional<TeamEntity> optionalTeamEntity = repository.findById(teamName);
        TeamEntity entity=new TeamEntity();
        if(optionalTeamEntity.isPresent()){
            entity.setName(optionalTeamEntity.get().getName());
            entity.setDescription(optionalTeamEntity.get().getDescription());
            entity.setSelector(optionalTeamEntity.get().getSelector());
            entity.setCountry(optionalTeamEntity.get().getCountry());
            return entity;
        }else{
            throw new TeamNotFoundException("Team not exists in our system");
        }
    }



    //UPDATE PLAYER SERVICE
    public TeamEntity updateTeam(String teamName, TeamRequest request) throws TeamNotFoundException {
        Optional<TeamEntity> optionalTeamEntity = repository.findById(teamName);
        if(optionalTeamEntity.isPresent()){
            TeamEntity entity = TeamEntity.builder()
                    .name(teamName)
                    .description(request.desc)
                    .country(request.country)
                    .selector(request.selector)
                    .build();

            return repository.save(entity);
        }else{
            throw new TeamNotFoundException("Team not exists in our system");

        }

    }

    public boolean deleteTeam(String teamName) throws TeamNotFoundException {
        Optional<TeamEntity> optionalTeamEntity = repository.findById(teamName);
        if(optionalTeamEntity.isPresent()){
            repository.deleteById(teamName);
            return true;
        }else{
            throw new TeamNotFoundException("Team not exists in our system");

        }

    }
}
