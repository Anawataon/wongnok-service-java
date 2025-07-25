package com.ttb.wongnok.mapper.difficulty;

import com.ttb.wongnok.model.domain.Difficulty;
import com.ttb.wongnok.model.entity.DifficultyEntity;

public class DifficultyDomainMapper {
    public static DifficultyEntity fromDomain(Difficulty domain) {
        DifficultyEntity entity = new DifficultyEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());
        return entity;
    }

    public static Difficulty toDomain(DifficultyEntity entity) {
        Difficulty domain = new Difficulty();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setCreatedAt(entity.getCreatedAt());
        domain.setUpdatedAt(entity.getUpdatedAt());
        return domain;
    }
}
