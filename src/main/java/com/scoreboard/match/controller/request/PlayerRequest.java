package com.scoreboard.match.controller.request;

import com.scoreboard.match.entity.TeamEntity;
import lombok.Data;

import java.util.Date;

@Data
public class PlayerRequest {
    public int playerId;
    public String playerName;
    public Date playerDob;
    public String playerCountry;
    public TeamEntity teamEntityId;
}
