package com.scoreboard.match.exception;

public class PlayerAlreadyExistException extends Throwable {
    public PlayerAlreadyExistException(String errorMessage) {
        super(errorMessage);

    }
}
