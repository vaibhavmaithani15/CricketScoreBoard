package com.scoreboard.match.exception;

public class TeamAlreadyExistException extends Throwable {
    public TeamAlreadyExistException(String errorMessage) {
        super(errorMessage);
    }
}
