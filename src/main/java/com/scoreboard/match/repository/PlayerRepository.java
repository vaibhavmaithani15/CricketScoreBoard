package com.scoreboard.match.repository;

import com.scoreboard.match.entity.PlayerEntity;
import com.scoreboard.match.entity.UserEntity;
import org.hibernate.query.NativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<PlayerEntity,Integer> {

    @Query("Select p from PlayerEntity p where p.teamEntity.name=?1")
    public List<PlayerEntity> findByTeamEntity(String teamName);

}
