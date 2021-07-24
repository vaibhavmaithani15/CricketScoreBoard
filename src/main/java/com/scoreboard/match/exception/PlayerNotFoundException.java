package com.scoreboard.match.exception;

public class PlayerNotFoundException extends Throwable {
    public PlayerNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}

