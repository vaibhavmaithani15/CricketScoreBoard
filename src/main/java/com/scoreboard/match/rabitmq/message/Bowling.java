package com.scoreboard.match.rabitmq.message;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Bowling {
    public String position;
    public String name;
    public String over;
    public String maiden;
    public String econ;
    public String wicket;
    public String six;
    public String boundary;
}
