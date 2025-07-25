package com.ttb.wongnok.model.domain;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Recipe {
    private long id;
    private String name;
    private String description;
    private String ingredient;
    private String instruction;
    private String imageUrl;
    private long cookingDurationID; 
    private CookingDuration cookingDuration;
    private long difficultyID;
    private Difficulty difficulty;
    private Instant createdAt;
    private Instant updatedAt;
}
