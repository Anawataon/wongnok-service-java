package com.ttb.wongnok.model.entity;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity // บอก Spring ว่า class นี้เป็น JPA entity → ใช้ map กับ table ในฐานข้อมูล
@Table(name = "ratings") // กำหนดชื่อ table ที่จะ map กับ class นี้
public class RatingEntity {
    @Id // ระบุว่า field นี้คือ Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ระบุวิธี generate id โดยให้ database auto-increment
    private long id;

    private int score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_recipe_id", nullable = false)
    private RecipeEntity foodRecipe;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;
}
