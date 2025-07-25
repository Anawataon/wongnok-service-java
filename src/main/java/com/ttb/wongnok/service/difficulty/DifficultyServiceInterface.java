package com.ttb.wongnok.service.difficulty;

import java.util.Optional;

import com.ttb.wongnok.model.domain.Difficulty;

public interface DifficultyServiceInterface {
    Optional<Difficulty> get(long id);
    
}