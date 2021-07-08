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
//@Table(name = "team")
//@NoArgsConstructor
//@AllArgsConstructor
public class Team {
    private int teamId;
    private String teamName;
    private String teamDesc;
    private String teamSelector;
    private String teamCountry;

}
