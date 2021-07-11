package com.scoreboard.match.repository;

import com.scoreboard.match.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<TeamEntity,String> {
}
