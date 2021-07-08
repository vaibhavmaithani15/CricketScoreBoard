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
//@Table(name = "wicket")
//@NoArgsConstructor
//@AllArgsConstructor
public class Wicket {
    private int wicketId;
    private BallerScore ballerId;
    private BatsmanScore batsmanId;
    private Match matchId;

}
