package com.scoreboard.match.rabitmq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface Messaging {

    String MATCHSCORE = "matchscore";

    @Input (MATCHSCORE)
    MessageChannel getMyMessageChannel();

}