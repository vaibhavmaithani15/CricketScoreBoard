package com.scoreboard.match.rabitmq.message;

import lombok.Data;

import java.util.List;
@Data
public class Score {
    public List<Bating> bating;
    public List<Bowling> bowling;
}
