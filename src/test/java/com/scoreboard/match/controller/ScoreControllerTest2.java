/*
package com.scoreboard.match.controller;

import com.scoreboard.match.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "spring.datasource.url=jdbc:mysql://localhost:3308/scoreboard")
public class ScoreControllerTest2 {

    @Autowired
    WebTestClient webTestClient;


    @Test
    public void testValidMatch() throws Exception {

        UserEntity user = UserEntity.builder()
                .password("test")
                .enabled(1)
                .role("admin")
                .firstName("test")
                .lastName("test")
                .userName("test12")
                .userCreatedBy("test")
                .build();


        webTestClient
                .post()
                .uri("/score/add")
                .bodyValue(user)
                .exchange()
                .expectStatus().is2xxSuccessful();


    }
}
*/
