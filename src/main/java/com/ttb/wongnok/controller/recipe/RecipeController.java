package com.ttb.wongnok.controller.recipe;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttb.wongnok.mapper.recipe.RecipeDtoMapper;
import com.ttb.wongnok.model.domain.Recipe;
import com.ttb.wongnok.model.dto.BaseResponse;
import com.ttb.wongnok.model.dto.RecipeRequest;
import com.ttb.wongnok.model.dto.RecipeResponse;
import com.ttb.wongnok.service.recipe.RecipeServiceInterface;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private final RecipeServiceInterface recipeService;

    public RecipeController(RecipeServiceInterface recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<RecipeResponse>>> getRecipes() {
        log.info("Retrieve recipes");

        List<Recipe> response = recipeService.get();
        List<RecipeResponse> recipeResponses = response.stream()
                .map(RecipeDtoMapper::toResponse)
                .toList();

        log.info("Recipes retrieved successfully");
        BaseResponse<List<RecipeResponse>> baseResponse = new BaseResponse<>(recipeResponses,
                "Recipes retrieved successfully");

        return ResponseEntity.status(HttpStatus.OK).body(baseResponse);
    }

    @PostMapping
    public ResponseEntity<BaseResponse<RecipeResponse>> createRecipe(@RequestBody @Validated RecipeRequest request) {
        Recipe domain = RecipeDtoMapper.fromRequest(request);

        Recipe response = recipeService.create(domain);

        RecipeResponse recipeResponse = RecipeDtoMapper.toResponse(response);
        BaseResponse<RecipeResponse> baseResponse = new BaseResponse<>(recipeResponse, "Recipe created successfully");

        return ResponseEntity.status(HttpStatus.CREATED).body(baseResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<RecipeResponse>> updateRecipe(@PathVariable Long id,
            @RequestBody @Validated RecipeRequest request) {
        Recipe domain = RecipeDtoMapper.fromRequest(request);

        Recipe response = recipeService.update(domain, id);

        RecipeResponse recipeResponse = RecipeDtoMapper.toResponse(response);
        BaseResponse<RecipeResponse> baseResponse = new BaseResponse<>(recipeResponse, "Recipe created successfully");

        return ResponseEntity.status(HttpStatus.OK).body(baseResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<String>> deleteRecipe(@PathVariable Long id) {

        recipeService.delete(id);

        BaseResponse<String> baseResponse = new BaseResponse<>(null, "Recipe deleted successfully");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(baseResponse);
    }
}
