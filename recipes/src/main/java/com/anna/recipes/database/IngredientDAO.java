package com.anna.recipes.database;

import com.anna.recipes.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngredientDAO extends JpaRepository<Ingredient, Long> {
    Optional<Ingredient> findById(int id);

}
