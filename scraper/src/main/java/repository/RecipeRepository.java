package repository;

import database.*;
import model.*;

import java.sql.SQLException;

public class RecipeRepository {


    private final Database db;
    private final CategoryDAO categoryDAO;
    private final ScraperDAO scraperDAO;
    private final DifficultyDAO difficultyDAO;
    private final NutritionInfoDAO nutritionInfoDAO;
    private final RecipeDAO recipeDAO;
    private final StepDAO stepDAO;
    private final IngredientGroupDAO ingredientGroupDAO;
    private final IngredientDAO ingredientDAO;
    private final IngredientGroupHasIngredientDAO ingredientGroupHasIngredientDAO;

    public RecipeRepository(Database db) {
        categoryDAO = new CategoryDAO(db);
        scraperDAO = new ScraperDAO(db);
        difficultyDAO = new DifficultyDAO(db);
        nutritionInfoDAO = new NutritionInfoDAO(db);
        recipeDAO = new RecipeDAO(db);
        stepDAO = new StepDAO(db);
        ingredientGroupDAO = new IngredientGroupDAO(db);
        ingredientDAO = new IngredientDAO(db);
        ingredientGroupHasIngredientDAO = new IngredientGroupHasIngredientDAO(db);

        this.db = db;
    }

    public void insert(Recipe r) throws SQLException {
        r.category = categoryDAO.findOrInsert(r.category.description);

        r.scraper = scraperDAO.findOrInsert(r.scraper.description);

        r.difficulty = difficultyDAO.findOrInsert(r.difficulty.description);

        nutritionInfoDAO.insert(r.nutritionInfo);

        recipeDAO.insert(r);


        for (Step step : r.steps) {
            step.recipe_id = r.id;
            stepDAO.insert(step);
        }

        for (IngredientGroup group : r.ingredientGroups) {
            group.recipe_id = r.id;
            ingredientGroupDAO.insert(group);

            for (Ingredient ingredient : group.ingredients) {
                ingredient = ingredientDAO.findOrInsert(ingredient.description);
                ingredientGroupHasIngredientDAO.insert(new IngredientGroupHasIngredient(group.id, ingredient.id));
            }
        }

    }

}
