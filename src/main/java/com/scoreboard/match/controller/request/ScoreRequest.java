package com.scoreboard.match.controller.request;


import com.scoreboard.match.controller.request.validator.ValidMatch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@ValidMatch
@AllArgsConstructor
public class ScoreRequest {
    @NonNull
    public Integer matchId;
    @NonNull
    public Integer ballerId;
    @NonNull
    public Integer batsmanId;
    public Integer runs;
    public boolean isOut;
    public boolean isBold;
    public Integer catchBy;
    public Integer runoutBy;
    public Integer stumpBy;
    public boolean whiteBall;
    public boolean noBall;
    public boolean bouncerBall;


}
