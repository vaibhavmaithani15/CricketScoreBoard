package com.scoreboard.match.controller;

import com.scoreboard.match.controller.request.TeamRequest;
import com.scoreboard.match.entity.TeamEntity;
import com.scoreboard.match.exception.TeamAlreadyExistException;
import com.scoreboard.match.exception.TeamNotFoundException;
import com.scoreboard.match.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/team")
public class TeamController {

    private TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }


    //POST MAPPING
    @PostMapping(path="/add",consumes = "application/json",produces = "application/json")
    public ResponseEntity<? extends Object> addTeam(@RequestBody TeamRequest request){
        try {
            TeamEntity entity= teamService.addTeam(request);
            if(entity!=null)
                return new ResponseEntity<TeamEntity>(entity, HttpStatus.OK);
        } catch (TeamAlreadyExistException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }



    //GET MAPPING WITH ALL Team
    @GetMapping(path = "/get",produces = "application/json")
    public ResponseEntity<TeamEntity> getAllTeam(){
        try {
            List<TeamEntity> allTeam = teamService.getAllTeam();
            if(!allTeam.isEmpty()){
                return new ResponseEntity(allTeam,HttpStatus.OK);
            }
        } catch (TeamNotFoundException e) {
            return  new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // GET MAPPING WITH SINGLE USER
    @GetMapping(path = "/get/{teamName}",produces = "application/json")
    public ResponseEntity<TeamEntity> getUser(@PathVariable String teamName){
        try {
            TeamEntity team=teamService.getTeam(teamName);
            if(team!=null){
                return new ResponseEntity<>(team,HttpStatus.OK);
            }
        } catch (TeamNotFoundException e) {
            return  new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //UPDATE MAPPING WITH SINGLE USER
    @PutMapping(path = "/update/{teamName}",consumes = "application/json",produces="application/json")
    public ResponseEntity<TeamEntity> updateUser(@PathVariable String teamName, @RequestBody TeamRequest request){
        try {
            TeamEntity updatedTeam = teamService.updateUser(teamName, request);
            if(updatedTeam!=null){
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (TeamNotFoundException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // DELETE MAPPING WITH SINGLE USER
    @DeleteMapping(path = "/delete/{teamName}",produces = "application/json")
    public ResponseEntity<TeamEntity> deleteTeam(@PathVariable String teamName){
        try {
            boolean deletedTeam = teamService.deleteTeam(teamName);
            if(deletedTeam){
                return  new ResponseEntity<TeamEntity>(HttpStatus.OK);
            }
        } catch (TeamNotFoundException e) {
            return  new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

