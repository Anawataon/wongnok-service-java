package com.ttb.wongnok.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

// ถ้า class มี public modifier → ชื่อ class ต้องตรงกับชื่อไฟล์ .java 
// และมีแค่ 1 class เท่านั้นที่เป็น public ได้ในไฟล์นั้น
// public record = คลาสที่สร้างมาเพื่อเก็บข้อมูลเท่านั้น (Immutable + Auto generate ทุกอย่างให้) 
// record => ไม่มี setName() ให้แก้ค่าได้ ใช้เหมาะกับ DTO (Data Transfer Object) หรือ Model ที่อ่านอย่างเดียว
public record RecipeRequest(
    @NotBlank String name,
    @NotBlank String description,
    @NotBlank String ingredient,
    @NotBlank String instruction,
    @NotBlank String imageUrl,
    @Min(1) long cookingDurationID,
    @Min(1) long difficultyID
) {}