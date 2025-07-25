package com.ttb.wongnok.mapper.recipe;

import com.ttb.wongnok.model.domain.Recipe;
import com.ttb.wongnok.model.dto.CookingDurationResponse;
import com.ttb.wongnok.model.dto.DifficultyResponse;
import com.ttb.wongnok.model.dto.RecipeRequest;
import com.ttb.wongnok.model.dto.RecipeResponse;

// dto <-> domain
public class RecipeDtoMapper {
    public static Recipe fromRequest(RecipeRequest dto){
        Recipe recipe = new Recipe();
        recipe.setName(dto.name());
        recipe.setDescription(dto.description());
        recipe.setIngredient(dto.ingredient());
        recipe.setInstruction(dto.instruction());
        recipe.setImageUrl(dto.imageUrl());
        recipe.setCookingDurationID(dto.cookingDurationID());
        recipe.setDifficultyID(dto.difficultyID());
        return recipe;
    }

    public static RecipeResponse toResponse(Recipe model) {
        return new RecipeResponse(
            model.getId(),
            model.getName(),
            model.getDescription(),
            model.getIngredient(),
            model.getInstruction(),
            model.getImageUrl(),
            new CookingDurationResponse(model.getCookingDuration().getId(), model.getCookingDuration().getName()),
            new DifficultyResponse(model.getDifficulty().getId(), model.getDifficulty().getName()),
            model.getCreatedAt(),
            model.getUpdatedAt()
        );
    }
}
