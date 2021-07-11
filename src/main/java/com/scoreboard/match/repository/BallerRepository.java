package com.scoreboard.match.repository;

import com.scoreboard.match.entity.BallerScoreEntity;
import com.scoreboard.match.entity.BallerScoreId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BallerRepository extends JpaRepository<BallerScoreEntity, BallerScoreId> {
}
