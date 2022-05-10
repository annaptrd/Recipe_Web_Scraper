package factories;

import model.IngredientGroup;
import model.Step;

import java.util.ArrayList;
import java.util.List;

public class StepFactory extends BaseFactory {
    public static Step create() {
        return new Step(faker.lorem().sentence(), faker.number().randomDigit());
    }

    public static List<Step> create(int n)  {
        List<Step> steps = new ArrayList<>();

        for (int i=0;i<n;i++) {
            steps.add(create());
        }

        return steps;
    }

}
