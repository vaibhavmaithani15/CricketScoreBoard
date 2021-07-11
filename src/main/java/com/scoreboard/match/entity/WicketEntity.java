package com.scoreboard.match.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Data
@Builder
@Table(name = "wicket")
@NoArgsConstructor
@AllArgsConstructor
public class WicketEntity {
    @Id
    private int wicketId;
    private int ballerId;
    private int batsmanId;
    private int catchBy;
    private int runoutBy;
    private int stumpBy;
    private int matchId;

}
