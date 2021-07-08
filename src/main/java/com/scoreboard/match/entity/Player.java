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
//@Table(name = "player")
//@NoArgsConstructor
//@AllArgsConstructor
public class Player {
    private int playerId;
    private String playerName;
    private Date playerDob;
    private String playerCountry;
    private Team teamId;
}
