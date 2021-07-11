package com.scoreboard.match.controller.request;

import lombok.Data;

@Data
public class ScoreRequest {
    public int matchId;
    public int ballerId;
    public int batsmanId;
    public int runs;
    public boolean isOut;
    public boolean isBold;
    public int catchBy;
    public int runoutBy;
    public int stumpBy;
    public boolean whiteBall;
    public boolean noBall;
    public boolean bouncerBall;

}
