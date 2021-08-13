package com.scoreboard.match.rabitmq.message;

import lombok.Data;

@Data
public class Bating {
    public String position;
    public String name;
    public int ball;
    public int run;
    public int boundary;
    public int six;
    public float sRate;
}
