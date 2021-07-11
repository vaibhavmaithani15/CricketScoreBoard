package com.scoreboard.match.controller;

import com.scoreboard.match.controller.request.ScoreRequest;
import com.scoreboard.match.service.ScoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/score")
public class ScorerController {
    private ScoreService scoreService;

    public ScorerController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }


    @PostMapping(path="/add",consumes = "application/json",produces = "application/json")
    public ResponseEntity addScore(@RequestBody ScoreRequest request){
        boolean success=scoreService.enterScore(request);
        if(success){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
