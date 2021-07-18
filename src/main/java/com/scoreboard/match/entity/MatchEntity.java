package com.scoreboard.match.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "first_team_name", referencedColumnName = "name")
    private TeamEntity firstTeamName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "second_team_name", referencedColumnName = "name")
    private TeamEntity secondTeamName;
    private String result;
    private Date matchDate;
    private String umpire;
    private String country;
    private String city;
    private String stadium;


}


