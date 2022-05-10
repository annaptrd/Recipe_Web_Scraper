package factories;

import model.*;
import org.apache.commons.lang3.builder.Diff;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//public List<IngredientGroup> ingredientGroups = new ArrayList<>();
//public List<Step> steps = new ArrayList<>();
//public NutritionInfo nutritionInfo = new NutritionInfo();
//public Difficulty difficulty = new Difficulty();
//public Category category = new Category();
//public Scraper scraper = new Scraper();
//public String title;
//public String description;
//public int preparation_time ;
//public int cooking_time ;
//public String servings;
//public int number_of_ingredients;

public class RecipeFactory extends BaseFactory {

    private static Difficulty getRandomDifficulty() {
        Random r = new Random();
        int x = r.nextInt(3);

        switch (x) {
            case 0:
                return new Difficulty(1, "Easy");
            case 1:
                return new Difficulty(1, "Medium");
            case 2:
                return new Difficulty(1, "Hard");
        }

        return new Difficulty(1, "Easy");
    }

    public static Recipe create() {
        Recipe r = new Recipe(faker.lorem().sentence());

        r.uri = faker.internet().url();
        r.title = faker.food().dish();
        r.description = faker.lorem().paragraph();
        r.preparation_time = faker.number().numberBetween(10, 60);
        r.cooking_time = faker.number().numberBetween(10, 60);
        r.servings = faker.number().numberBetween(1, 10) + " servings ";
        r.scraped_at = faker.date().birthday();
        r.writer = faker.name().fullName();
        r.difficulty = getRandomDifficulty();
        r.comments = faker.lorem().sentence();
        r.scraper = ScraperFactory.create();
        r.nutritionInfo = NutritionInfoFactory.create();
        r.number_of_ingredients = faker.number().numberBetween(1, 10);

        r.category = CategoryFactory.create();
        r.steps = StepFactory.create(faker.number().numberBetween(1, 10));
        r.ingredientGroups = IngredientGroupFactory.create(faker.number().numberBetween(1, 10));

        for (IngredientGroup group : r.ingredientGroups) {
            group.ingredients = IngredientFactory.create(faker.number().numberBetween(1, 10));
        }


        return r;
    }

    public static List<Recipe> create(int n) {
        List<Recipe> list = new ArrayList<>();

        for (int i=0;i<n;i++) {
            list.add(create());
        }

        return list;
    }
}
