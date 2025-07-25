package com.ttb.wongnok.mapper.recipe;

import com.ttb.wongnok.mapper.cookingduration.CookingDurationDomainMapper;
import com.ttb.wongnok.mapper.difficulty.DifficultyDomainMapper;
import com.ttb.wongnok.model.domain.Recipe;
import com.ttb.wongnok.model.entity.RecipeEntity;

// domain <-> entity
public class RecipeDomainMapper {
    public static RecipeEntity fromDomain(Recipe domain) {
        RecipeEntity entity = new RecipeEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setDescription(domain.getDescription());
        entity.setIngredient(domain.getIngredient());
        entity.setInstruction(domain.getInstruction());
        entity.setImageUrl(domain.getImageUrl());
        entity.setCookingDuration(CookingDurationDomainMapper.fromDomain(domain.getCookingDuration()));
        entity.setDifficulty(DifficultyDomainMapper.fromDomain(domain.getDifficulty()));
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());
        return entity;
    }

    public static Recipe toDomain(RecipeEntity entity) {
        Recipe domain = new Recipe();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setDescription(entity.getDescription());
        domain.setIngredient(entity.getIngredient());
        domain.setInstruction(entity.getInstruction());
        domain.setImageUrl(entity.getImageUrl());
        domain.setCookingDuration(CookingDurationDomainMapper.toDomain(entity.getCookingDuration()));
        domain.setDifficulty(DifficultyDomainMapper.toDomain(entity.getDifficulty()));
        domain.setCreatedAt(entity.getCreatedAt());
        domain.setUpdatedAt(entity.getUpdatedAt());
        return domain;
    }
}
