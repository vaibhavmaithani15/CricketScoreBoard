package com.scoreboard.match.exception;

public class UserAlreadyExistException extends Throwable {
    public UserAlreadyExistException(String errorMessage) {
        super(errorMessage);
    }
}
