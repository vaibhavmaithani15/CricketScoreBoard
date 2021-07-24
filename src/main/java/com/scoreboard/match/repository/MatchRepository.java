package com.scoreboard.match.repository;

import com.scoreboard.match.entity.MatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<MatchEntity, Integer> {


}
