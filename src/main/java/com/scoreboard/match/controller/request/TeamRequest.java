package com.scoreboard.match.controller.request;

import lombok.Data;
import lombok.NonNull;

@Data
public class TeamRequest {
    @NonNull
    public String name;
    public String desc;
    @NonNull
    public String selector;
    @NonNull
    public String country;

}
