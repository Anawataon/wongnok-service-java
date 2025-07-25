package com.ttb.wongnok.service.difficulty;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ttb.wongnok.mapper.difficulty.DifficultyDomainMapper;
import com.ttb.wongnok.model.domain.Difficulty;
import com.ttb.wongnok.model.entity.DifficultyEntity;
import com.ttb.wongnok.repository.difficulty.DifficultyRepository;

@Service
public class DifficultyService implements DifficultyServiceInterface {

    private final DifficultyRepository difficultyRepo;

    public DifficultyService(DifficultyRepository difficultyRepo) {
        this.difficultyRepo = difficultyRepo;
    }

    public Optional<Difficulty> get(long id) {
        Optional<DifficultyEntity> difficulty = difficultyRepo.findById(id);

        // map() จะคืนค่า Optional.empty() ถ้า difficulty เป็น empty
        // และจะคืนค่า Optional<Difficulty> ถ้าไม่เป็น empty
        // ดังนั้นโค้ดจะกระชับและอ่านง่ายขึ้น
        // functional programming style
        // โดยใช้ DifficultyDomainMapper เพื่อแปลง DifficultyEntity เป็น Difficulty
        return difficulty.map(DifficultyDomainMapper::toDomain);
    }
}
