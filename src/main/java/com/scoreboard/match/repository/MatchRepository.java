package com.scoreboard.match.repository;

import com.scoreboard.match.entity.MatchEntity;
import com.scoreboard.match.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface MatchRepository extends JpaRepository<MatchEntity, Integer> {

}
