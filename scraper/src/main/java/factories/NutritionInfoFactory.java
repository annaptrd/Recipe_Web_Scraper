package factories;

import model.NutritionInfo;
import model.Scraper;

import java.util.ArrayList;
import java.util.List;

public class NutritionInfoFactory extends BaseFactory {
    public static NutritionInfo create() {
        NutritionInfo info = new NutritionInfo();
        info.kcal = faker.number().numberBetween(0,300);
        info.carbohydrates = faker.number().numberBetween(0,80);
        info.protein = faker.number().numberBetween(0,80);
        info.fat = faker.number().numberBetween(0,80);
        info.saturated_fat= faker.number().numberBetween(0,80);
        info.sugars= faker.number().numberBetween(0,80);
        return info;
    }

    public static List<NutritionInfo> create(int n)  {
        List<NutritionInfo> collection = new ArrayList<>();

        for (int i=0;i<n;i++) {
            collection.add(create());
        }

        return collection;
    }


}
