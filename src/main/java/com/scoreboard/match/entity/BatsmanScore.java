package com.scoreboard.match.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

//@Entity
//@Data
//@Builder
//@Table(name = "batsman_score")
//@NoArgsConstructor
//@AllArgsConstructor
public class BatsmanScore {
    private int bastmanScoreId;
    private Match matchId;
    private int noOfSixes;
    private int noOfFours;
    private int noOfThrees;
    private int noOfTows;
    private int noOfOne;
    private Player playerId;
    private Team teamId;
    private int totalRun;
    private String status;
    private String reasonOfOut;
    private int playerOver;
    private String outType;
    private int outByPlayer;
    private int noOfBallsPlayed;
}
