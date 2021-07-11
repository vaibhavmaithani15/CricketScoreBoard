package com.scoreboard.match.exception;

public class TeamNotFoundException extends Throwable {
    public TeamNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
