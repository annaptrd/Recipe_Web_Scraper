package com.anna.recipes.service;


import com.anna.recipes.database.RecipeDAO;
import com.anna.recipes.dto.SearchDTO;
import com.anna.recipes.model.Category;
import com.anna.recipes.model.Ingredient;
import com.anna.recipes.model.IngredientGroup;
import com.anna.recipes.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeService {
    @Autowired
    RecipeDAO dao;

    public Optional<Recipe> findById(int id) {
        return dao.findById(id);
    }

    public List<Recipe> list() {
        return dao.findAll();
    }

    public List<Recipe> latest() {
        return dao.latest();
    }

    private boolean contains(Set<String> set, String key) {
        key = key.toLowerCase();

        for (String sentence : set) {
            if (sentence.contains(key)) {
                return true;
            }
        }
        return false;
    }

    public List<Recipe> search(SearchDTO input) {
        List<Recipe> candidates =  dao.findAll();
        List<Recipe> results = new ArrayList<>();

        for (Recipe r:  candidates) {
            boolean matches = true;

            if (matches && input.getTitle().isPresent() && !r.getTitle().toLowerCase().contains(input.getTitle().get().toLowerCase())) {
                matches = false;
            }

            if (matches && input.getTotalIngredients().isPresent() && r.getNumberOfIngredients() > input.getTotalIngredients().get()) {
                matches = false;
            }

            if (matches && input.getCalories().isPresent() && r.getNutritionInfoId().getCalories() > input.getCalories().get()) {
                matches = false;
            }


            if (matches && input.getTime().isPresent() && r.getPreparationTime() + r.getCookingTime() > input.getTime().get()) {
                matches = false;
            }

            if (matches && input.getDifficulty().isPresent() && !r.getDifficultyId().getDescription().toLowerCase().contains(input.getDifficulty().get().toLowerCase())) {
                matches = false;
            }

            if (matches && input.getWriter().isPresent() && !r.getWriter().toLowerCase().contains(input.getWriter().get().toLowerCase())) {
                matches = false;
            }

            if (matches && input.getServings().isPresent() && !r.getServings().toLowerCase().contains(input.getServings().get().toLowerCase())) {
                matches = false;
            }

            if (matches && (!input.getCategories().isEmpty())) {
                String recipe_category = preprocess(r.getCategoryId().getDescription());

                if (!input.getCategories().contains(recipe_category)) {
                    matches = false;
                }
            }

            if (matches && input.getDate_min().isPresent() && !input.getDate_max().isPresent() ) {
                if (r.getScrapedAt().compareTo(input.getDate_min().get()) < 0) {
                    matches = false;
                }
            }

            if (matches && !input.getDate_min().isPresent() && input.getDate_max().isPresent() ) {
                if (r.getScrapedAt().compareTo(input.getDate_max().get()) > 0) {
                    matches = false;
                }
            }

            if (matches && input.getDate_min().isPresent() && input.getDate_max().isPresent() ) {
                if (r.getScrapedAt().compareTo(input.getDate_min().get()) < 0) {
                    matches = false;
                }

                if (r.getScrapedAt().compareTo(input.getDate_max().get()) > 0) {
                    matches = false;
                }
            }

            if (matches && (!input.getIngredients().isEmpty() || !input.getExclude_ingredients().isEmpty())) {
                Set<String> recipe_ingredients = new HashSet<>();

                for (IngredientGroup g : r.getIngredientGroupList()) {
                    for (Ingredient i : g.getIngredientList()) {
                        String preprocess = preprocess(i.getDescription());
                        recipe_ingredients.add(preprocess);
                    }
                }

                for (String ingredient_description : input.getIngredients()) {
                    if (!contains(recipe_ingredients, ingredient_description)) {
                        matches = false;
                        break;
                    }
                }

                if (matches) {
                    for (String ingredient_description : input.getExclude_ingredients()) {
                        if (contains(recipe_ingredients, ingredient_description)) {
                            matches = false;
                            break;
                        }
                    }
                }
            }

            if (matches) {
                results.add(r);
            }
        }

        return results;
    }

    private String preprocess(String description) {
        // TODO: improve text processing
        return description.replaceAll("\\d", "").toLowerCase();
    }

    // select * from recipe
    //	where MATCH(title,description) against('chicken' IN NATURAL LANGUAGE MODE);
    public List<Recipe> findSimilarById(int id) {
        return dao.findSimilar(id);
    }
}
