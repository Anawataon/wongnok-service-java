package com.ttb.wongnok.mapper.cookingduration;

import com.ttb.wongnok.model.domain.CookingDuration;
import com.ttb.wongnok.model.entity.CookingDurationEntity;

public class CookingDurationDomainMapper {
    public static CookingDurationEntity fromDomain(CookingDuration domain) {
        CookingDurationEntity entity = new CookingDurationEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());
        return entity;
    }

    public static CookingDuration toDomain(CookingDurationEntity entity) {
        CookingDuration domain = new CookingDuration();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setCreatedAt(entity.getCreatedAt());
        domain.setUpdatedAt(entity.getUpdatedAt());
        return domain;
    }
}
