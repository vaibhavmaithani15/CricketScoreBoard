package com.scoreboard.match.controller;

import com.scoreboard.match.controller.request.TeamRequest;
import com.scoreboard.match.entity.TeamEntity;
import com.scoreboard.match.exception.TeamAlreadyExistException;
import com.scoreboard.match.exception.TeamNotFoundException;
import com.scoreboard.match.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins="*")
@RestController
@RequestMapping(path = "/team")
public class TeamController {

    private TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }


    //POST MAPPING
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<? extends Object> addTeam(@Valid @RequestBody TeamRequest request) {
        try {
            TeamEntity entity = teamService.addTeam(request);
            if (entity != null)
                return new ResponseEntity<TeamEntity>(entity, HttpStatus.OK);
        } catch (TeamAlreadyExistException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //GET MAPPING WITH ALL Team
    @GetMapping(path = "/get", produces = "application/json")
    public ResponseEntity<TeamEntity> getAllTeam() {
        try {
            List<TeamEntity> allTeam = teamService.getAllTeam();
            if (!allTeam.isEmpty()) {
                return new ResponseEntity(allTeam, HttpStatus.OK);
            }
        } catch (TeamNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // GET MAPPING WITH SINGLE USER
    @GetMapping(path = "/get/{teamName}", produces = "application/json")
    public ResponseEntity<TeamEntity> getTeam(@PathVariable String teamName) {
        try {
            TeamEntity team = teamService.getTeam(teamName);
            if (team != null) {
                return new ResponseEntity<>(team, HttpStatus.OK);
            }
        } catch (TeamNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //UPDATE MAPPING WITH SINGLE Team
    @PutMapping(path = "/update/{teamName}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<TeamEntity> updateTeam(@PathVariable String teamName, @RequestBody TeamRequest request) {
        try {
            TeamEntity updatedTeam = teamService.updateTeam(teamName, request);
            if (updatedTeam != null) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (TeamNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // DELETE MAPPING WITH SINGLE TEAM
    @DeleteMapping(path = "/delete/{teamName}", produces = "application/json")
    public ResponseEntity<TeamEntity> deleteTeam(@PathVariable String teamName) {
        try {
            boolean deletedTeam = teamService.deleteTeam(teamName);
            if (deletedTeam) {
                return new ResponseEntity<TeamEntity>(HttpStatus.OK);
            }
        } catch (TeamNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    //Search Team functionality
    @GetMapping(path = "/search/{keyword}",produces = "application/json")
    public ResponseEntity<TeamEntity> searchTeam(@PathVariable String keyword ){
        try {
            List<TeamEntity> searchedTeams = teamService.searchTeam(keyword);
            if (!searchedTeams.isEmpty()) {
                return new ResponseEntity(searchedTeams, HttpStatus.OK);
            }
        }catch (TeamNotFoundException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}


