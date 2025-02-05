package com.baas.backend.repository;

import com.baas.backend.model.GameSave;
import com.baas.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GameSaveRepository extends JpaRepository<GameSave, Long> {
    List<GameSave> findByUserOrderByCreatedAtDesc(User user);
    Optional<GameSave> findTopByUserOrderByCreatedAtDesc(User user);
}