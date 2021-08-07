package com.scoreboard.match.service;

import com.scoreboard.match.controller.request.PlayerRequest;
import com.scoreboard.match.controller.request.WicketRequest;
import com.scoreboard.match.entity.*;
import com.scoreboard.match.exception.PlayerNotFoundException;
import com.scoreboard.match.exception.WicketAlreadyExistException;
import com.scoreboard.match.exception.WicketNotFoundException;
import com.scoreboard.match.repository.MatchRepository;
import com.scoreboard.match.repository.WicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class WicketService {
    private WicketRepository repository;
    private MatchRepository matchRepository;

    public WicketService(WicketRepository repository, MatchRepository matchRepository) {
        this.repository = repository;
        this.matchRepository = matchRepository;
    }

    public WicketEntity addPlayer(WicketRequest request) throws WicketAlreadyExistException {

        Optional<WicketEntity> wicketEntity = repository.findById(request.wicketId);
        Optional<MatchEntity> matchEntity = matchRepository.findById(request.matchId);
        if (wicketEntity.isPresent()) {
            throw new WicketAlreadyExistException("Wicket already exist in our system please select another wicket Id");
        } else {
            WicketEntity entity = WicketEntity.builder()
                    .batsmanId(request.batsmanId)
                    .ballerId(request.ballerId)
                    .stumpBy(request.stumpBy)
                    .runoutBy(request.runoutBy)
                    .catchBy(request.catchBy)
                    .matchId(matchEntity.get())
                    .build();

            return repository.save(entity);

        }

    }


    //GET ALL PLAYER SERVICE
    public List<WicketEntity> getAllWicket() throws WicketNotFoundException {
        List<WicketEntity> wickets = repository.findAll();
        if (wickets.isEmpty()) {
            throw new WicketNotFoundException("Wicket not exists in our system");
        } else {
            return wickets;
        }
    }

    //GET SINGLE Wicket SERVICE
    public WicketEntity getWicket(int wicketId) throws WicketNotFoundException {
        Optional<WicketEntity> optionalWicketEntity = repository.findById(wicketId);
        WicketEntity entity = new WicketEntity();
        if (optionalWicketEntity.isPresent()) {
            entity.setBallerId(optionalWicketEntity.get().getBallerId());
            entity.setBatsmanId(optionalWicketEntity.get().getBatsmanId());
            entity.setCatchBy(optionalWicketEntity.get().getCatchBy());
            entity.setRunoutBy(optionalWicketEntity.get().getRunoutBy());
            entity.setMatchId(optionalWicketEntity.get().getMatchId());
            entity.setStumpBy(optionalWicketEntity.get().getStumpBy());

            return entity;
        } else {
            throw new WicketNotFoundException("Player not exists in our system");
        }
    }

    //update user service
    public WicketEntity updateWicket(int wicketId, WicketRequest request) throws WicketNotFoundException {
        Optional<WicketEntity> optionalWicketEntity = repository.findById(wicketId);

        if (optionalWicketEntity.isPresent()) {
            WicketEntity entity = WicketEntity.builder()
                    .wicketId(wicketId)
                    .ballerId(request.ballerId)
                    .batsmanId(request.batsmanId)
                    .catchBy(request.catchBy)
                    .runoutBy(request.runoutBy)
                    .stumpBy(request.stumpBy)
                    .matchId(optionalWicketEntity.get().getMatchId())
                    .build();

            return repository.save(entity);
        } else {
            throw new WicketNotFoundException("Wicket not exists in our system");

        }

    }

    //delete a player service
    public boolean deleteWicket(int wicketId) throws WicketNotFoundException {
        Optional<WicketEntity> optionalWicketEntity = repository.findById(wicketId);
        if (optionalWicketEntity.isPresent()) {
            repository.deleteById(wicketId);
            return true;
        } else {
            throw new WicketNotFoundException("Team not exists in our system");

        }

    }
}
