package com.scoreboard.match.controller.request;

import com.scoreboard.match.controller.request.validator.ValidTeam;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
public class PlayerRequest {
    @NonNull
    public int playerId;
    @NonNull
    public String playerName;
    @NonNull
    public Date playerDob;
    @NonNull
    public String playerCountry;
    @ValidTeam
    public String teamEntityId;
}

//Remove player id