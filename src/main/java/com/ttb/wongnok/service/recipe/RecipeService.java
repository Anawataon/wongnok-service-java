package com.ttb.wongnok.service.recipe;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttb.wongnok.mapper.cookingduration.CookingDurationDomainMapper;
import com.ttb.wongnok.mapper.difficulty.DifficultyDomainMapper;
import com.ttb.wongnok.mapper.recipe.RecipeDomainMapper;
import com.ttb.wongnok.model.domain.CookingDuration;
import com.ttb.wongnok.model.domain.Difficulty;
import com.ttb.wongnok.model.domain.Recipe;
import com.ttb.wongnok.model.entity.RecipeEntity;
import com.ttb.wongnok.repository.recipe.RecipeRepository;
import com.ttb.wongnok.service.cookingduration.CookingDurationServiceInterface;
import com.ttb.wongnok.service.difficulty.DifficultyServiceInterface;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RecipeService implements RecipeServiceInterface {

    private final RecipeRepository recipeRepo;
    private final CookingDurationServiceInterface cookingService;
    private final DifficultyServiceInterface difficultyService;

    public RecipeService(RecipeRepository recipeRepo,
            CookingDurationServiceInterface cookingService,
            DifficultyServiceInterface difficultyService) {
        this.recipeRepo = recipeRepo;
        this.cookingService = cookingService;
        this.difficultyService = difficultyService;
    }

    public List<Recipe> get() {
        // functional programming style using Java Stream API
        List<Recipe> list = recipeRepo.findAll() // List<Recipe>
                .stream() // แปลง List<RecipeEntity> ให้เป็น Java Stream => Stream<RecipeEntity>
                .map(RecipeDomainMapper::toDomain) // คือการ “แปลง” ข้อมูลใน stream แต่ละตัว RecipeEntity => Recipe
                .toList(); // Convert the Stream<Recipe> back to List<Recipe>

        // Stream คือ ลำดับข้อมูลแบบ functional ที่สามารถประมวลผลทีละรายการ (แบบ
        // declarative)
        // เราทำแบบนี้เพื่อ แปลง หรือจัดการข้อมูล ต่อด้วย .map(), .filter(), ฯลฯ
        return list;
    }

    @Transactional
    public Recipe create(Recipe recipe) {
        Optional<CookingDuration> cd = cookingService.get(recipe.getCookingDurationID());
        if (cd.isEmpty()) {
            throw new EntityNotFoundException("CookingDuration not found with id: " + recipe.getCookingDurationID());
        }

        Optional<Difficulty> df = difficultyService.get(recipe.getDifficultyID());
        if (df.isEmpty()) {
            throw new EntityNotFoundException("Difficulty not found with id: " + recipe.getDifficultyID());
        }

        recipe.setCookingDuration(cd.get());
        recipe.setDifficulty(df.get());

        RecipeEntity recipeEntity = RecipeDomainMapper.fromDomain(recipe);
        RecipeEntity response = recipeRepo.save(recipeEntity);

        return RecipeDomainMapper.toDomain(response);
    }

    @Transactional
    public Recipe update(Recipe recipe, Long recipeID) {
        // ตรวจสอบว่ามี recipe อยู่หรือไม่
        RecipeEntity existingEntity = recipeRepo.findById(recipeID)
                .orElseThrow(() -> new EntityNotFoundException("Recipe not found with id: " + recipeID));

        Optional<CookingDuration> cd = cookingService.get(recipe.getCookingDurationID());
        if (cd.isEmpty()) {
            throw new EntityNotFoundException("CookingDuration not found with id: " + recipe.getCookingDurationID());
        }

        Optional<Difficulty> df = difficultyService.get(recipe.getDifficultyID());
        if (df.isEmpty()) {
            throw new EntityNotFoundException("Difficulty not found with id: " + recipe.getDifficultyID());
        }

        recipe.setCookingDuration(cd.get());
        recipe.setDifficulty(df.get());

        // อัปเดตเฉพาะ field ที่ต้องการ แทนการสร้าง entity ใหม่
        existingEntity.setName(recipe.getName());
        existingEntity.setDescription(recipe.getDescription());
        existingEntity.setIngredient(recipe.getIngredient());
        existingEntity.setInstruction(recipe.getInstruction());
        existingEntity.setImageUrl(recipe.getImageUrl());
        existingEntity.setCookingDuration(CookingDurationDomainMapper.fromDomain(recipe.getCookingDuration()));
        existingEntity.setDifficulty(DifficultyDomainMapper.fromDomain(recipe.getDifficulty()));
        existingEntity.setUpdatedAt(Instant.now());

        RecipeEntity savedEntity = recipeRepo.save(existingEntity); // save ทำ update ให้เมื่อมี ID

        return RecipeDomainMapper.toDomain(savedEntity);
    }

    @Transactional
    public void delete(Long recipeID) {
        if (!recipeRepo.existsById(recipeID)) {
            throw new EntityNotFoundException("Recipe not found with id: " + recipeID);
        }
        recipeRepo.deleteById(recipeID);
    }
}
