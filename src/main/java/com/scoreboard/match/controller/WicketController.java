package com.scoreboard.match.controller;

import com.scoreboard.match.controller.request.PlayerRequest;
import com.scoreboard.match.controller.request.WicketRequest;
import com.scoreboard.match.entity.PlayerEntity;
import com.scoreboard.match.entity.WicketEntity;
import com.scoreboard.match.exception.PlayerNotFoundException;
import com.scoreboard.match.exception.WicketAlreadyExistException;
import com.scoreboard.match.exception.WicketNotFoundException;
import com.scoreboard.match.service.WicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping(path = "/wicket")
@CrossOrigin(origins = "*")
public class WicketController {


    private WicketService wicketService;
    public WicketController(WicketService wicketService) {
        this.wicketService = wicketService;
    }




    // POST MAPPING FOR PLAYER CONTROLLER
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<? extends Object> addWicket(@RequestBody WicketRequest request) {
        try {
            WicketEntity entity = wicketService.addPlayer(request);
            if (entity != null)
                return new ResponseEntity<WicketEntity>(entity, HttpStatus.OK);
        } catch (WicketAlreadyExistException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    //GET MAPPING WITH ALL Wicket
    @GetMapping(path = "/get", produces = "application/json")
    public ResponseEntity<WicketEntity> getAllPWicket() {
        try {
            List<WicketEntity> allWicket = wicketService.getAllWicket();
            if (!allWicket.isEmpty()) {
                return new ResponseEntity(allWicket, HttpStatus.OK);
            }
        } catch (WicketNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // GET MAPPING WITH SINGLE wicket using wicketId
    @GetMapping(path = "/get/{wicketId}", produces = "application/json")
    public ResponseEntity<WicketEntity> getWicket(@PathVariable int wicketId) {
        try {
            WicketEntity wicket = wicketService.getWicket(wicketId);
            if (wicket != null) {
                return new ResponseEntity<>(wicket, HttpStatus.OK);
            }
        } catch (WicketNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //PUT MAPPING WITH SINGLE Wicket
    @PutMapping(path = "/update/{wicketId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<WicketEntity> updateWicket(@PathVariable int wicketId, @RequestBody WicketRequest request) {
        try {
            WicketEntity updatedWicket = wicketService.updateWicket(wicketId, request);
            if (updatedWicket != null) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (WicketNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    // DELETE MAPPING WITH SINGLE TEAM
    @DeleteMapping(path = "/delete/{wicketId}", produces = "application/json")
    public ResponseEntity<WicketEntity> deleteWicket(@PathVariable int wicketId) {
        try {
            boolean deletedWicket = wicketService.deleteWicket(wicketId);
            if (deletedWicket) {
                return new ResponseEntity<WicketEntity>(HttpStatus.OK);
            }
        } catch (WicketNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
