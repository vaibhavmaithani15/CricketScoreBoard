package com.scoreboard.match.exception;

public class PlayerNotFoundInTeamException extends Exception {
    public PlayerNotFoundInTeamException(String errorMessage) {
        super(errorMessage);
    }
}
