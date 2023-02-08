package com.anna.recipes.mapper;

import com.anna.recipes.dto.RecipeDTO;
import com.anna.recipes.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeMapper {
    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    NutritionInfoMapper nutritionInfoMapper;

    @Autowired
    DifficultyMapper difficultyMapper;

    @Autowired
    ScraperMapper scraperMapper;

    @Autowired
    StepMapper stepMapper;

    @Autowired
    IngredientGroupMapper ingredientGroupMapper;


    public RecipeDTO toDTO(Recipe entity) {
        RecipeDTO dto = new RecipeDTO();

        dto.setId(entity.getId());
        dto.setUri(entity.getUri());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setHashValue(entity.getHashValue());
        dto.setPreparationTime(entity.getPreparationTime());
        dto.setCookingTime(entity.getCookingTime());
        dto.setServings(entity.getServings());
        dto.setScrapedAt(entity.getScrapedAt());
        dto.setWriter(entity.getWriter());
        dto.setComments(entity.getComments());
        dto.setNumberOfIngredients(entity.getNumberOfIngredients());
        dto.setPhoto_url(entity.getPhoto_url());

        dto.setCategory(categoryMapper.toDTO(entity.getCategoryId()));
        dto.setNutritionInfo(nutritionInfoMapper.toDTO(entity.getNutritionInfoId()));
        dto.setDifficulty(difficultyMapper.toDTO(entity.getDifficultyId()));
        dto.setScraper(scraperMapper.toDTO(entity.getScraperId()));
        dto.setSteps(stepMapper.toDTO(entity.getStepList()));
        dto.setIngredientGroups(ingredientGroupMapper.toDTO(entity.getIngredientGroupList()));

        dto.setTotalTime((entity.getCookingTime() + entity.getPreparationTime()) + " minutes");

        dto.setTotalCalories(entity.getNutritionInfoId().getCalories() + " kcal");

// dto.setCategoryId(Category categoryId);
// dto.setDifficultyId(Difficulty difficultyId);
// dto.setNutritionInfoId(NutritionInfo nutritionInfoId);
// dto.setScraperId(Scraper scraperId);
// dto.setStepList(List<Step> stepList);
// dto.setIngredientGroupList(List<IngredientGroup> ingredientGroupList);



        return dto;
    }

    public List<RecipeDTO> toDTO(List<Recipe> entities) {
        List<RecipeDTO> dtos = new ArrayList<>();

        for (Recipe c : entities) {
            dtos.add(toDTO(c));
        }
        return dtos;
    }
}
