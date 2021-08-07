package com.scoreboard.match.repository;

import com.scoreboard.match.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<TeamEntity, String> {

    public List<TeamEntity> findByNameContaining(String name);
}
