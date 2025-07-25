package com.ttb.wongnok.model.entity;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Getter // ใช้ Lombok เพื่อสร้าง getter อัตโนมัติ
@Setter // ใช้ Lombok เพื่อสร้าง setter อัตโนมัติ
@Entity // บอก Spring ว่า class นี้เป็น JPA entity → ใช้ map กับ table ในฐานข้อมูล
@Table(name = "food_recipes") // กำหนดชื่อ table ที่จะ map กับ class นี้
public class RecipeEntity {
    @Id // ระบุว่า field นี้คือ Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ระบุวิธี generate id โดยให้ database auto-increment
    private long id;

    private String name;
    private String description;
    private String ingredient;
    private String instruction;

    // ระบุว่า field imageUrl จะ map กับ column ชื่อว่า image_url (เพราะชื่อ field
    // กับชื่อ column ไม่ตรงกัน)
    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne // ความสัมพันธ์แบบ Many-to-One (recipe หลายอันต่อ 1 cooking duration)
    @JoinColumn(name = "cooking_duration_id") // กำหนดชื่อ foreign key ใน table food_recipes
    private CookingDurationEntity cookingDuration; // cookingDuration จะ map กับ entity CookingDuration

    @ManyToOne
    @JoinColumn(name = "difficulty_id") // กำหนดชื่อ foreign key ใน table difficulties
    private DifficultyEntity difficulty; // difficulty จะ map กับ entity Difficulty

    @CreationTimestamp // ใส่ timestamp ให้ field createdAt โดยอัตโนมัติตอน insert
    private Instant createdAt;

    @UpdateTimestamp // ใส่ timestamp ให้ field updatedAt โดยอัตโนมัติตอน insert
    private Instant updatedAt;
}