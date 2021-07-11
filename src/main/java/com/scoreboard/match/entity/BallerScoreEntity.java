package com.scoreboard.match.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@IdClass(BallerScoreId.class)
@Data
@Builder
@Table(name = "baller_score")
@NoArgsConstructor
@AllArgsConstructor
public class BallerScoreEntity {
    @Id
    public int ballerId;
    @Id
    public int matchId;
    public int wicketId;
    public int noOfOvers;
    public int noOfSixes;
    public int noOfFours;
    public int noOfThrees;
    public int noOfTwos;
    public int noOfOnes;
    public int medianOver;
    public int missedBall;
    public int whiteBall;
    public int noBall;
    public int bouncerBall;
}


