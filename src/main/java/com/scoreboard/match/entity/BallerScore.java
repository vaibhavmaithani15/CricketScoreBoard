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
//@Table(name = "baller_score")
//@NoArgsConstructor
//@AllArgsConstructor
public class BallerScore {
    private int ballerScoreId;
    private Match matchId;
    private Player playerId;
    private int noOfOvers;
    private int noOfSixes;
    private int noOfFour;
    private int noOfThrees;
    private int noOfTows;
    private int noOfOnes;
    private int medinOver;
    private int missedBall;
    private int whiteBall;
    private int noBall;
    private int bouncerBall;
}
