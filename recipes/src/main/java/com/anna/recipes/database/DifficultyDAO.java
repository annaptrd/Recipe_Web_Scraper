package com.anna.recipes.database;

import com.anna.recipes.model.Difficulty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DifficultyDAO extends JpaRepository<Difficulty, Long> {
    Optional<Difficulty> findById(int id);

}
