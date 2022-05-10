package factories;

import model.Category;
import model.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class IngredientFactory extends BaseFactory {
    public static Ingredient create() {
        return new Ingredient(faker.lorem().sentence());
    }

    public static List<Ingredient> create(int n) {
        List<Ingredient> ingredients = new ArrayList<>();

        for (int i=0;i<n;i++) {
            ingredients.add(create());
        }

        return ingredients;
    }
}
