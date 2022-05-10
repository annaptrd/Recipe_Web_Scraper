package model;

import java.util.ArrayList;
import java.util.List;

public class IngredientGroup {
    public List<Ingredient> ingredients = new ArrayList<>();
    public long id;
    public long recipe_id;
    public String description;

    public  IngredientGroup(){

    }
    public IngredientGroup(long recipe_id, String description) {
        this.recipe_id = recipe_id;
        this.description = description;
    }

    public IngredientGroup(String description) {
        this.description = description;
    }

    public IngredientGroup(long id, long recipe_id, String description) {
        this.id = id;
        this.recipe_id = recipe_id;
        this.description = description;
    }
}
