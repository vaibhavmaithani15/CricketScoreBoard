package com.scoreboard.match.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity

@Data
@Builder
@Table(name = "cricket_match")
@NoArgsConstructor
@AllArgsConstructor
public class MatchEntity {
    @Id
    private int matchId;
    private String firstTeamName;
    private String secondTeamName;
    private String result;
    private Date matchDate;
    private String umpire;
    private String country;
    private String city;
    private String stadium;


}


