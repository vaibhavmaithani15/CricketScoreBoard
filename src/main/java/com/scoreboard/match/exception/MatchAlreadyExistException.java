package com.scoreboard.match.exception;

public class MatchAlreadyExistException extends Exception {
    public MatchAlreadyExistException(String errorMessage){
        super(errorMessage);
    }
}
