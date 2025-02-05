package com.baas.backend.model;


import jakarta.persistence.*;
import lombok.Setter;

@Entity
@Table(name = "UserScores")
public class UserScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private int score;
}