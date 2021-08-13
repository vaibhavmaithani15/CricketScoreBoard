package com.scoreboard.match.rabitmq.message;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.scoreboard.match.controller.request.ScoreRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ScoreTest {
ObjectMapper objectMapper;
    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
    }
    @Test
    public void testScoreRequestParsing() throws IOException {
        Score score = objectMapper.readValue(new File("src/test/resources/SampleData/sample_rabbitmq_message.json"), Score.class);
        assertNotNull(score);
        List<Bating> bating = score.getBating();
        assertEquals(2,bating.size());
        List<Bowling> bowling = score.getBowling();
        assertEquals(2,bowling.size());
        bating.stream()
                .filter(batsman->batsman.position.equals("bating"))
                .forEach(e->{
                   assertEquals("Virat Kholi",e.name);
                   assertEquals(20,e.ball);
                   assertEquals(10,e.run);
                   assertEquals(2,e.boundary);
                });

    }
}