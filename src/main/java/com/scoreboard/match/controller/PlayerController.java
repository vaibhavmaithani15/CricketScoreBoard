package com.scoreboard.match.controller;

import com.scoreboard.match.controller.request.PlayerRequest;
import com.scoreboard.match.entity.PlayerEntity;
import com.scoreboard.match.exception.PlayerAlreadyExistException;
import com.scoreboard.match.exception.PlayerNotFoundException;
import com.scoreboard.match.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/player")
public class PlayerController {
    private PlayerService playerService;


    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }


    // POST MAPPING FOR PLAYER CONTROLLER
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<? extends Object> addPlayer(@Valid @RequestBody PlayerRequest request) {
        try {
            PlayerEntity entity = playerService.addPlayer(request);
            if (entity != null)
                return new ResponseEntity<PlayerEntity>(entity, HttpStatus.OK);
        } catch (PlayerAlreadyExistException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //GET MAPPING WITH ALL Ployer
    @GetMapping(path = "/get", produces = "application/json")
    public ResponseEntity<PlayerEntity> getAllPlayer() {
        try {
            List<PlayerEntity> allPlayer = playerService.getAllPlayer();
            if (!allPlayer.isEmpty()) {
                return new ResponseEntity(allPlayer, HttpStatus.OK);
            }
        } catch (PlayerNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // GET MAPPING WITH SINGLE USER using playerId
    @GetMapping(path = "/get/{playerId}", produces = "application/json")
    public ResponseEntity<PlayerEntity> getUser(@PathVariable int playerId) {
        try {
            PlayerEntity player = playerService.getPlayer(playerId);
            if (player != null) {
                return new ResponseEntity<>(player, HttpStatus.OK);
            }
        } catch (PlayerNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //Get mapping for all player of a particular team
//    @GetMapping(path = "/get/{teamName}",produces = "application/json")
    @RequestMapping(value = "/{teamName}", method = RequestMethod.GET)
    public ResponseEntity<PlayerEntity> getAllPlayerOfParticularTeam(@PathVariable String teamName) {
        try {
            List<PlayerEntity> allPlayer = playerService.getAllPlayerInTeam(teamName);
            if (!allPlayer.isEmpty()) {
                return new ResponseEntity(allPlayer, HttpStatus.OK);
            }
        } catch (PlayerNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //PUT MAPPING WITH SINGLE PLAYER
    @PutMapping(path = "/update/{playerId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<PlayerEntity> updateUser(@Valid @PathVariable int playerId, @RequestBody PlayerRequest request) {
        try {
            PlayerEntity updatedPlayer = playerService.updatePlayer(playerId, request);
            if (updatedPlayer != null) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (PlayerNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    // DELETE MAPPING WITH SINGLE TEAM
    @DeleteMapping(path = "/delete/{playerId}", produces = "application/json")
    public ResponseEntity<PlayerEntity> deleteTeam(@PathVariable int playerId) {
        try {
            boolean deletedPlayer = playerService.deletePlayer(playerId);
            if (deletedPlayer) {
                return new ResponseEntity<PlayerEntity>(HttpStatus.OK);
            }
        } catch (PlayerNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
