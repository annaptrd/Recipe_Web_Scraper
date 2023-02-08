package com.anna.recipes.dto;

import com.anna.recipes.model.IngredientGroup;
import com.anna.recipes.model.NutritionInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RecipeDTO {

    private Integer id;
    private String uri;
    private String photo_url;
    private String title;
    private String description;
    private String hashValue;
    private int preparationTime;
    private int cookingTime;
    private String servings;
    private Date scrapedAt;
    private String writer;
    private String comments;
    private int numberOfIngredients;
    private DifficultyDTO difficulty;
    private CategoryDTO category;
    private NutritionInfoDTO nutritionInfo;
    private ScraperDTO scraper;
    private List<StepDTO> steps;
    private List<IngredientGroupDTO> ingredientGroups;

    private String totalCalories;
    private String totalTime;
//    private Difficulty difficultyId;
//
//    private Scraper scraperId;
//
//    private List<IngredientGroup> ingredientGroupList;

}
