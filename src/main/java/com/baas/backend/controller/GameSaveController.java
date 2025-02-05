package com.baas.backend.controller;

import com.baas.backend.model.GameSave;
import com.baas.backend.model.User;
import com.baas.backend.repository.GameSaveRepository;
import com.baas.backend.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/saves")
public class GameSaveController {

    private final GameSaveRepository gameSaveRepository;
    private final UserRepository userRepository;

    public GameSaveController(GameSaveRepository gameSaveRepository, UserRepository userRepository) {
        this.gameSaveRepository = gameSaveRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<?> saveGame(@RequestBody SaveRequest saveRequest,
                                      @AuthenticationPrincipal User userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        GameSave gameSave = new GameSave();
        gameSave.setUser(user);
        gameSave.setSaveData(saveRequest.getSaveData());
        gameSaveRepository.save(gameSave);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<SaveResponse> loadGame(@AuthenticationPrincipal User userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        GameSave save = gameSaveRepository.findTopByUserOrderByCreatedAtDesc(user)
                .orElseThrow(() -> new RuntimeException("No save found"));

        return ResponseEntity.ok(new SaveResponse(save.getSaveData()));
    }
}

