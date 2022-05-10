package com.anna.recipes.database;

import com.anna.recipes.model.NutritionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NutritionInfoDAO extends JpaRepository<NutritionInfo, Long> {
    Optional<NutritionInfo> findById(int id);

}
