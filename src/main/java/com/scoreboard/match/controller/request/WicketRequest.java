package com.scoreboard.match.controller.request;

import lombok.NonNull;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

public class WicketRequest {

    @NonNull
    public int ballerId;
    @NonNull
    public int batsmanId;
    public int catchBy;
    public int runoutBy;
    public int stumpBy;

    public int matchId;
    public int wicketId;
}
