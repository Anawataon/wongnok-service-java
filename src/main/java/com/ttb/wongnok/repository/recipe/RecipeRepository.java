package com.ttb.wongnok.repository.recipe;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ttb.wongnok.model.entity.RecipeEntity;

public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {}
