package com.ttb.wongnok.service.recipe;

import java.util.List;

import com.ttb.wongnok.model.domain.Recipe;

public interface RecipeServiceInterface {
    Recipe create(Recipe recipe);
    Recipe update(Recipe recipe, Long recipeID);
    void delete(Long recipeID);
    List<Recipe> get();
}
