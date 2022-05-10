package com.anna.recipes.database;

import com.anna.recipes.model.Scraper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScraperDAO extends JpaRepository<Scraper, Long> {
    Optional<Scraper> findById(int id);

}
