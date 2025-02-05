package com.baas.backend.repository;

import com.baas.backend.model.User;
import com.baas.backend.model.UserScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserScoreRepository extends JpaRepository<UserScore, Integer> {
    List<UserScore> findByUserOrderByCreatedAtDesc(User user);
    Optional<UserScore> findTopByUserOrderByCreatedAtDesc(User user);
}
