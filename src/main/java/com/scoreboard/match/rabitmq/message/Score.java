package com.scoreboard.match.rabitmq.message;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class Score {
    public List<Bating> bating;
    public List<Bowling> bowling;
}
