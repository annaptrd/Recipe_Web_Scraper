package com.anna.recipes.database;

import com.anna.recipes.model.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StepDAO extends JpaRepository<Step, Long> {
    Optional<Step> findById(int id);

}
