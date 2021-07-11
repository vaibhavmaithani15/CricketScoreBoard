package com.scoreboard.match.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(BatsmanScoreId.class)
@Data
@Builder
@Table(name = "batsman_score")
@NoArgsConstructor
@AllArgsConstructor
public class BatsmanScoreEntity {
    @Id
    private int batsmanId;
    @Id
    private int matchId;
    private int noOfSixes;
    private int noOfFours;
    private int noOfThrees;
    private int noOfTwos;
    private int noOfOnes;
    private int noOfZero;
    private int wicketBy;
    private int reasonOfOut;

}


