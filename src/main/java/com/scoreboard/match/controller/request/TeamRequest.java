package com.scoreboard.match.controller.request;

import com.scoreboard.match.controller.request.validator.ValidTeam;
import lombok.Data;
import lombok.NonNull;

@Data
public class TeamRequest {
    @NonNull
    @ValidTeam
    public String name;
    public String desc;
    @NonNull
    public String selector;
    @NonNull
    public String country;

}
