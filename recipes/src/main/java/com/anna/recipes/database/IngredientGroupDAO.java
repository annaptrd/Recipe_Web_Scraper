package com.anna.recipes.database;

import com.anna.recipes.model.IngredientGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngredientGroupDAO extends JpaRepository<IngredientGroup, Long> {
    Optional<IngredientGroup> findById(int id);

}
