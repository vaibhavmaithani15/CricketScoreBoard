package com.scoreboard.match.controller.request;

import com.scoreboard.match.controller.request.validator.ValidTeam;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;
@Data
public class MatchRequest {
    public int matchId;
    @NonNull
    @ValidTeam
    public String firstTeamName;//aka batting team
    @NonNull
    @ValidTeam
    public String secondTeamName;//aka bowling team
    @NonNull
    public String result;
    @NonNull
    public Date matchDate;
    @NonNull
    public String umpire;
    @NonNull
    public String country;
    @NonNull
    public String city;
    @NonNull
    public String stadium;
}
