package com.scoreboard.match.exception;

public class MatchNotFoundException extends Exception {
    public MatchNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
