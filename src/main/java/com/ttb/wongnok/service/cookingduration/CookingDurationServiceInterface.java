package com.ttb.wongnok.service.cookingduration;

import java.util.Optional;

import com.ttb.wongnok.model.domain.CookingDuration;

public interface CookingDurationServiceInterface {
    Optional<CookingDuration> get(long id);
}
