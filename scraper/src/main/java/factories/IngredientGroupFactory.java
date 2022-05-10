package factories;

import model.Ingredient;
import model.IngredientGroup;

import java.util.ArrayList;
import java.util.List;

public class IngredientGroupFactory extends BaseFactory {
    public static IngredientGroup create() {
        return new IngredientGroup(faker.lorem().sentence());
    }

    public static List<IngredientGroup> create(int n) {
        List<IngredientGroup> groups = new ArrayList<>();

        for (int i=0;i<n;i++) {
            groups.add(create());
        }

        return groups;
    }
}
