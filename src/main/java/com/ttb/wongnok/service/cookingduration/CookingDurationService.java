package com.ttb.wongnok.service.cookingduration;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ttb.wongnok.mapper.cookingduration.CookingDurationDomainMapper;
import com.ttb.wongnok.model.domain.CookingDuration;
import com.ttb.wongnok.model.entity.CookingDurationEntity;
import com.ttb.wongnok.repository.cookingduration.CookingDurationRepository;

@Service
public class CookingDurationService implements CookingDurationServiceInterface{

    private final CookingDurationRepository cookingRepo;

    public CookingDurationService(CookingDurationRepository cookingRepo) {
        this.cookingRepo = cookingRepo;
    }

    public Optional<CookingDuration> get(long id) {
        Optional<CookingDurationEntity> cookingDuration = cookingRepo.findById(id);

        // map() จะคืนค่า Optional.empty() ถ้า cookingDuration เป็น empty
        // และจะคืนค่า Optional<CookingDuration> ถ้าไม่เป็น empty
        // ดังนั้นโค้ดจะกระชับและอ่านง่ายขึ้น
        // functional programming style
        // โดยใช้ CookingDurationDomainMapper เพื่อแปลง CookingDurationEntity เป็น CookingDuration
        return cookingDuration.map(CookingDurationDomainMapper::toDomain);
    }
}
