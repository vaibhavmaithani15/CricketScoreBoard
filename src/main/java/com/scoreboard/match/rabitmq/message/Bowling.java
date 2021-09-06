package com.scoreboard.match.rabitmq.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bowling {
    public String position;
    public String name;
    public float over;
    public int maiden;
    public float econ;
    public int wicket;
    public int six;
    public int boundary;
}
