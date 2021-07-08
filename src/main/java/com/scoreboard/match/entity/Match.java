package com.scoreboard.match.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
//@Entity
//@Data
//@Builder
//@Table(name = "match")
//@NoArgsConstructor
//@AllArgsConstructor
public class Match {
    private int matchId;
    private int firstTeamId;
    private int secondTeamId;
    private String matchResult;
    private Date matchDate;
    private String matchUmpire;
    private String matchCountry;
    private String matchCity;
    private String matchStadium;
    private Team teamId;

}
