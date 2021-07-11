package com.scoreboard.match.service;

import com.scoreboard.match.controller.request.ScoreRequest;
import com.scoreboard.match.entity.*;
import com.scoreboard.match.repository.BallerRepository;
import com.scoreboard.match.repository.BatsmanRepository;
import com.scoreboard.match.repository.WicketRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScoreService {
    private BallerRepository ballerRepository;
    private BatsmanRepository batsmanRepository;
    private WicketRepository wicketRepository;

    public ScoreService(BallerRepository ballerRepository,
                        BatsmanRepository batsmanRepository,
                        WicketRepository wicketRepository) {
        this.ballerRepository = ballerRepository;
        this.batsmanRepository = batsmanRepository;
        this.wicketRepository = wicketRepository;
    }

    public boolean enterScore(ScoreRequest request) {
        if (request.isOut) {
            playerOut(request);
        } else {
            playerNotOut(request);
        }
        return true;
    }

    private void playerNotOut(ScoreRequest request) {
        // insert and update in baller and batsman table
        ballerEntry(request);
        batsmanEntry(request);
    }

    private void batsmanEntry(ScoreRequest request) {
        Optional<BatsmanScoreEntity> optional = batsmanRepository.findById(BatsmanScoreId.builder()
                .batsmanId(request.getBatsmanId())
                .matchId(request.getMatchId())
                .build());
        BatsmanScoreEntity batsman;
        if (optional.isPresent()) {
            batsman = optional.get();
            batsman.setNoOfSixes(request.runs == 6 ? batsman.getNoOfSixes() + 1 : batsman.getNoOfSixes());
            batsman.setNoOfFours(request.runs == 4 ? batsman.getNoOfFours() + 1 : batsman.getNoOfFours());
            batsman.setNoOfThrees(request.runs == 3 ? batsman.getNoOfThrees() + 1 : batsman.getNoOfThrees());
            batsman.setNoOfTwos(request.runs == 2 ? batsman.getNoOfTwos() + 1 : batsman.getNoOfTwos());
            batsman.setNoOfOnes(request.runs == 1 ? batsman.getNoOfOnes() + 1 : batsman.getNoOfOnes());
            batsman.setNoOfZero(request.runs == 0 ? batsman.getNoOfZero() + 1 : batsman.getNoOfZero());
        } else {
            batsman = BatsmanScoreEntity.builder()
                    .matchId(request.matchId)
                    .batsmanId(request.batsmanId)
                    .noOfSixes(request.runs == 6 ? 1 : 0)
                    .noOfFours(request.runs == 4 ? 1 : 0)
                    .noOfThrees(request.runs == 3 ? 1 : 0)
                    .noOfTwos(request.runs == 2 ? 1 : 0)
                    .noOfOnes(request.runs == 1 ? 1 : 0)
                    .noOfZero(request.runs == 0 ? 1 : 0)
                    .build();
        }
        batsmanRepository.save(batsman);
    }

    private void ballerEntry(ScoreRequest request) {
        Optional<BallerScoreEntity> optional = ballerRepository.findById(BallerScoreId.builder()
                .ballerId(request.ballerId)
                .matchId(request.matchId)
                .build());
        BallerScoreEntity baller;
        if (optional.isPresent()) {
            baller = optional.get();
            switch (request.runs) {
                case 0:
                    baller.setMissedBall(baller.getMissedBall() + 1);
                    break;
                case 1:
                    baller.setNoOfOnes(baller.getNoOfOnes() + 1);
                    break;
                case 2:
                    baller.setNoOfTwos(baller.getNoOfTwos() + 1);
                    break;
                case 3:
                    baller.setNoOfThrees(baller.getNoOfThrees() + 1);
                    break;
                case 4:
                    baller.setNoOfFours(baller.getNoOfFours() + 1);
                    break;
                case 6:
                    baller.setNoOfSixes(baller.getNoOfSixes() + 1);
                    break;
            }
            if (request.noBall) {
                baller.setNoBall(baller.getNoBall() + 1);
            } else if (request.whiteBall) {
                baller.setWhiteBall(baller.getWhiteBall() + 1);
            } else if (request.bouncerBall) {
                baller.setBouncerBall(baller.getBouncerBall() + 1);
            }

        } else {
            baller = BallerScoreEntity.builder()
                    .ballerId(request.ballerId)
                    .matchId(request.matchId)
                    .noOfSixes(request.runs == 6 ? 1 : 0)
                    .noOfFours(request.runs == 4 ? 1 : 0)
                    .noOfThrees(request.runs == 5 ? 1 : 0)
                    .noOfTwos(request.runs == 2 ? 1 : 0)
                    .noOfOnes(request.runs == 1 ? 1 : 0)
                    .bouncerBall(request.bouncerBall ? 1 : 0)
                    .missedBall(request.runs == 0 ? 1 : 0)
                    .whiteBall(request.whiteBall ? 1 : 0)
                    .noBall(request.noBall ? 1 : 0)
                    .build();

        }
        ballerRepository.save(baller);
    }

    private void playerOut(ScoreRequest request) {
        //wicket table -> insert
        WicketEntity wicketEntity = wicketRepository.save(WicketEntity.builder()
                .ballerId(request.ballerId)
                .batsmanId(request.batsmanId)
                .matchId(request.matchId)
                .catchBy(request.catchBy)
                .runoutBy(request.runoutBy)
                .stumpBy(request.stumpBy)
                .build());


        // baller table wicket_id -> update
        Optional<BallerScoreEntity> baller = ballerRepository.findById(BallerScoreId.builder()
                .ballerId(request.ballerId)
                .matchId(request.matchId)
                .build());
        baller.ifPresent(b -> {
            b.setWicketId(wicketEntity.getWicketId());
            ballerRepository.save(b);
        });


        // batsman table reason_of_out, wicket_by -> update
        Optional<BatsmanScoreEntity> batsman = batsmanRepository.findById(BatsmanScoreId.builder()
                .batsmanId(request.getBatsmanId())
                .matchId(request.getMatchId())
                .build());
        batsman.ifPresent(b -> {
            b.setWicketBy(request.ballerId);
            if (request.catchBy > 0)
                b.setReasonOfOut(3);
            if (request.runoutBy > 0)
                b.setReasonOfOut(2);
            if (request.stumpBy > 0)
                b.setReasonOfOut(6);

            batsmanRepository.save(b);
        });
    }
}
