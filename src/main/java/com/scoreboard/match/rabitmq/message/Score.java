package com.scoreboard.match.rabitmq.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Score {
    public List<Bating> bating;
    public List<Bowling> bowling;
}
