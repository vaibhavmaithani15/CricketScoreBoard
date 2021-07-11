package com.scoreboard.match.repository;

import com.scoreboard.match.entity.BatsmanScoreEntity;
import com.scoreboard.match.entity.BatsmanScoreId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatsmanRepository extends JpaRepository<BatsmanScoreEntity, BatsmanScoreId> {
}
