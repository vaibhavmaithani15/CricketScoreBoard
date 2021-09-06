package com.scoreboard.match.rabitmq.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bating {
    public String position;
    public String name;
    public int ball;
    public int run;
    public int boundary;
    public int six;
    public float sRate;
}
