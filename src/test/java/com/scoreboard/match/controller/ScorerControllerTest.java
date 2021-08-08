package com.scoreboard.match.controller;


import com.scoreboard.match.config.ScoreboardUserDetails;
import com.scoreboard.match.controller.request.ScoreRequest;
import com.scoreboard.match.entity.PlayerEntity;
import com.scoreboard.match.entity.UserEntity;
import com.scoreboard.match.rabitmq.PublishScore;
import com.scoreboard.match.repository.UserRepository;
import com.scoreboard.match.service.AuthenticationService;
import com.scoreboard.match.service.MatchService;
import com.scoreboard.match.service.ScoreService;
import com.scoreboard.match.util.TeamEnum;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@WebMvcTest(ScorerController.class)
@AutoConfigureMockMvc
public class ScorerControllerTest {
    String request1 = "{\n" +
            "  \"ballerId\": 30,\n" +
            "  \"batsmanId\": 2,\n" +
            "  \"bouncerBall\": false,\n" +
            "  \"catchBy\": 0,\n" +
            "  \"isBold\": false,\n" +
            "  \"isOut\": false,\n" +
            "  \"matchId\": 1,\n" +
            "  \"noBall\": false,\n" +
            "  \"runoutBy\": 0,\n" +
            "  \"runs\": 4,\n" +
            "  \"stumpBy\": 0,\n" +
            "  \"whiteBall\": false\n" +
            "}";
    String request2 = "{\n" +
            "  \"ballerId\": 15,\n" +
            "  \"batsmanId\": 42,\n" +
            "  \"bouncerBall\": false,\n" +
            "  \"catchBy\": 0,\n" +
            "  \"isBold\": false,\n" +
            "  \"isOut\": false,\n" +
            "  \"matchId\": 1,\n" +
            "  \"noBall\": false,\n" +
            "  \"runoutBy\": 0,\n" +
            "  \"runs\": 4,\n" +
            "  \"stumpBy\": 0,\n" +
            "  \"whiteBall\": false\n" +
            "}";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private LocalValidatorFactoryBean localValidatorFactoryBean;
    @MockBean
    private ScoreService scoreService;
    @MockBean
    private MatchService matchService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private PublishScore publishScore;
    @MockBean
    private AuthenticationService authenticationService;
//    @Test
//    public void testValidMatch() throws Exception {
//        when(matchService.getDetails(1)).thenReturn(mockMatchServiceDetails());
//
//        UserEntity user = UserEntity.builder()
//                .password("test")
//                .enabled(1)
//                .role("admin")
//                .firstName("test")
//                .lastName("test")
//                .userName("test12")
//                .userCreatedBy("test")
//                .build();
//
//
//        when(authenticationService.loadUserByUsername("Rahul")).thenReturn(new ScoreboardUserDetails(user));
//        when(scoreService.enterScore(new ScoreRequest(1, 30, 2, 4, false,
//                false, 0, 0, 0, false, false, false))).thenReturn(true);
//
//
//        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
//                .post("/score/add")
//                .content(request1)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//
//                .andExpect(MockMvcResultMatchers.status().is(200));
//        result.andExpect(MockMvcResultMatchers.status().is(200));
//
//
//    }
//
//    @Test
//    public void testInvalidMatch() throws Exception {
//        when(matchService.getDetails(1)).thenReturn(new HashMap<String, List<PlayerEntity>>());
//        when(scoreService.enterScore(new ScoreRequest(1, 15, 42, 4, false,
//                false, 0, 0, 0, false, false, false))).thenReturn(true);
//
//        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
//                .post("/score/add")
//                .content(request2)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().is(400));
//        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
//
//    }

    private Map<String, List<PlayerEntity>> mockMatchServiceDetails() {
        Map<String, List<PlayerEntity>> teams = new HashMap<>();
        List<PlayerEntity> firstTeamPlayers = new ArrayList<PlayerEntity>();
        firstTeamPlayers.add(PlayerEntity.builder()
                .playerId(42)
                .build());
        List<PlayerEntity> secondTeamPlayers = new ArrayList<>();
        secondTeamPlayers.add(PlayerEntity.builder()
                .playerId(15)
                .build());

        teams.put(TeamEnum.BattingTeam.toString(), firstTeamPlayers);
        teams.put(TeamEnum.BowlingTeam.toString(), secondTeamPlayers);
        return teams;
    }
}