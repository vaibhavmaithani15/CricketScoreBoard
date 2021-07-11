package com.scoreboard.match.repository;

import com.scoreboard.match.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,String> {



}
