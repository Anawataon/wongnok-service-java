package com.ttb.wongnok.model.dto;

import java.time.Instant;

public record RecipeResponse(
    long id,
    String name,
    String description,
    String ingredient,
    String instruction,
    String imageUrl,
    CookingDurationResponse cookingDuration,
    DifficultyResponse difficulty,
    Instant createdAt,
    Instant updatedAt
) {}
