package com.scoreboard.match.rabitmq.message;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Bowling {
    public String position;
    public String name;
    public int over;
    public int maiden;
    public int econ;
    public int wicket;
    public int six;
    public int boundary;
}
