package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Recipe {
    public long id;
    public List<IngredientGroup> ingredientGroups = new ArrayList<>();
    public List<Step> steps = new ArrayList<>();
    public NutritionInfo nutritionInfo = new NutritionInfo();
    public Difficulty difficulty = new Difficulty();
    public Category category = new Category();
    public Scraper scraper = new Scraper();
    public String uri;
    public String photo_url;
    public String title;
    public String description;
    public int preparation_time ;
    public int cooking_time ;
    public String servings;
    public int number_of_ingredients;
    public Date scraped_at;
    public String writer;
    public String comments;

    public Recipe(){

    }
    public Recipe(String description){
        this.description = description;
    }
    public void print() {
        System.out.println("Title             : " + title);
        System.out.println("URI               : " + uri);
        System.out.println("Photo URL         : " + photo_url);
        System.out.println("Writer            : " + writer);
        System.out.println("Scraped at        : " + scraped_at);
        System.out.println("Category          : " +  category);
        System.out.println("Scraper           : " +  scraper.description);
        System.out.println("Description       : " +  description);
        System.out.println("Difficulty        : " +  difficulty);
        System.out.println("Servings          : " +  servings);
        System.out.println("Preparation time  : " +  preparation_time);
        System.out.println("Cooking time     : " +  cooking_time);
        System.out.println("Number of steps   : " + steps.size());
        System.out.println("Number of ingredients   : " + number_of_ingredients);

        for (int i=0;i<steps.size();i++) {
            System.out.println(" - Step: " + steps.get(i));
        }

        for (int i=0;i<ingredientGroups.size();i++) {
            IngredientGroup group =  ingredientGroups.get(i);

            System.out.println("Group #" + (i+1) + ": " + group.description);

            List<Ingredient> ingredients = group.ingredients;

            for (int j=0;j<ingredients.size();j++) {
                System.out.println(" - Ingredient " + (j+1) + ": " + ingredients.get(j).description);
            }
        }

        System.out.println("Calories      : " + nutritionInfo.kcal);
        System.out.println("Protein       : " + nutritionInfo.protein + " gr");
        System.out.println("Fats          : " + nutritionInfo.fat + " gr");
        System.out.println("Carbohydrates : " + nutritionInfo.carbohydrates + " gr");
        System.out.println("Saturated Fat : " + nutritionInfo.saturated_fat + " gr");
        System.out.println("Sugars        : " + nutritionInfo.sugars + " gr");
    }
}