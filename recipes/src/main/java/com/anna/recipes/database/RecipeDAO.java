package com.anna.recipes.database;

import com.anna.recipes.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeDAO extends JpaRepository<Recipe, Long> {
    Optional<Recipe> findById(int id);

    @Query(nativeQuery = true, value = "SELECT * FROM recipe order by scraped_at desc limit 10")
    List<Recipe> latest();


    @Query(nativeQuery = true, value = "select * from recipe where MATCH(title,description) against((select title from recipe r where r.id = :id) IN NATURAL LANGUAGE MODE)")
    List<Recipe> findSimilar(int id);

}
