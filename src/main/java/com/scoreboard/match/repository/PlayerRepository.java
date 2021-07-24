package com.scoreboard.match.repository;

import com.scoreboard.match.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Integer> {

    @Query("Select p from PlayerEntity p where p.teamEntity.name=?1")
    public List<PlayerEntity> findByTeamEntity(String teamName);

}
