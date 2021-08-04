package com.scoreboard.match.controller;

import com.scoreboard.match.controller.request.MatchRequest;
import com.scoreboard.match.controller.request.PlayerRequest;
import com.scoreboard.match.entity.MatchEntity;
import com.scoreboard.match.entity.PlayerEntity;
import com.scoreboard.match.exception.MatchAlreadyExistException;
import com.scoreboard.match.exception.MatchNotFoundException;
import com.scoreboard.match.exception.PlayerAlreadyExistException;
import com.scoreboard.match.exception.PlayerNotFoundException;
import com.scoreboard.match.service.MatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping(path = "/match")
@CrossOrigin(origins = "*")
public class MatchController {
    private MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }
    // POST MAPPING FOR Match CONTROLLER
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<? extends Object> addMatch(@Valid @RequestBody MatchRequest request) {
        MatchEntity entity;
        try {
            entity = matchService.addMatch(request);
            if (entity != null)
                return new ResponseEntity<MatchEntity>(entity, HttpStatus.OK);
        } catch (MatchAlreadyExistException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //GET MAPPING WITH ALL Match
    @GetMapping(path = "/get", produces = "application/json")
    public ResponseEntity<MatchEntity> getAllMatch() {
        try {
            List<MatchEntity> allMatch = matchService.getAllMatches();
            if (!allMatch.isEmpty()) {
                return new ResponseEntity(allMatch, HttpStatus.OK);
            }
        } catch (MatchNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }



    // GET MAPPING WITH SINGLE Match using matchId
    @GetMapping(path = "/get/{matchId}", produces = "application/json")
    public ResponseEntity<MatchEntity> getMatch(@PathVariable int matchId) {
        try {
            MatchEntity match = matchService.getMatch(matchId);
            if (match != null) {
                return new ResponseEntity<>(match, HttpStatus.OK);
            }
        } catch (MatchNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //PUT MAPPING WITH SINGLE Match
    @PutMapping(path = "/update/{matchId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MatchEntity> updateMatch(@Valid @PathVariable int matchId, @RequestBody MatchRequest request) {
        MatchEntity updatedMatch;
        try {
            updatedMatch= matchService.updateMatch(matchId, request);
            if (updatedMatch != null) {
                return new ResponseEntity<>(updatedMatch,HttpStatus.OK);
            }
        } catch (MatchNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    // DELETE MAPPING WITH SINGLE Match
    @DeleteMapping(path = "/delete/{matchId}", produces = "application/json")
    public ResponseEntity<MatchEntity> deleteMatch(@PathVariable int matchId) {
        try {
            boolean deletedMatch = matchService.deleteMatch(matchId);
            if (deletedMatch) {
                return new ResponseEntity<MatchEntity>(HttpStatus.OK);
            }
        } catch (MatchNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(matchId,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
