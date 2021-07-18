package com.scoreboard.match;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scoreboard.match.controller.request.ScoreRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreServiceTest {
    ObjectMapper objectMapper;
    ScoreRequest scoreRequest;
    @BeforeEach
    public void setup()  {
        objectMapper=new ObjectMapper();
    }
    @Test
    public void testScoreRequestParsing() throws IOException {
        scoreRequest = objectMapper.readValue(new File("src/test/resources/SampleData/sample_request.json"), ScoreRequest.class);
        assertNotNull(scoreRequest);
        assertEquals(30,scoreRequest.ballerId);
    }
    @Test
    public void testScoreRequestValidateMandatoryField() throws IOException {
       assertThrows(JsonMappingException.class,()->{
           scoreRequest = objectMapper.readValue(new File("src/test/resources/SampleData/sample_score_request_error.json"), ScoreRequest.class);
       });
    }
}
