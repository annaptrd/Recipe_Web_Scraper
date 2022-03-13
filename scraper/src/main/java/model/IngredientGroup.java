package model;

import java.util.ArrayList;
import java.util.List;

public class IngredientGroup {
    public List<Ingredient> ingredients = new ArrayList<>();
    public String description;

    public IngredientGroup() {
        this("default");
    }

    public IngredientGroup(String description) {
        this.description = description;
    }
}
