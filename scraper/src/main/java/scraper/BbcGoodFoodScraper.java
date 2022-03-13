package scraper;

import model.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class BbcGoodFoodScraper extends BaseScraper {

    public BbcGoodFoodScraper() {
        super("BBC Scraper");
    }

    private String cleanUpTitle(String title) {
        return title.replace(" | BBC Good Food","");
    }

    public String[] removeFirstXElements(String[] arr, int  x) {
        String newArr[] = new String[arr.length - x];
        for (int i = 1; i < arr.length; i++) {
            newArr[i-1] = arr[i];
        }
        return newArr;
    }

    private int getPreparationTime(String time) {
        String [] fields = time.split(" ");
        String [] prep = fields[0].split(":");
        return Integer.parseInt(prep[1]);
    }

    private int getCookingTime(String time) {
        String [] fields = time.split(" ");
        String [] prep = fields[2].split(":");
        return Integer.parseInt(prep[1]);
    }
    private String[] splitNutritionInfo(String info) {
        String [] fields = info.split(" ");
        return fields;
    }

    private String getDifficultyLevel(String text) {
        return text;
    }

    private int getProtein(String text) {
        String [] fields = text.split("g");
        return Integer.parseInt(fields[0]);
    }

    private int getFat(String text) {
        String [] fields = text.split("g");
        return Integer.parseInt(fields[0]);
    }

    private int getCarbohydrates(String text) {
        String [] fields = text.split("g");
        return Integer.parseInt(fields[0]);
    }

    private int getCalories(String text) {
        String [] fields = text.split("g");
        return Integer.parseInt(fields[0]);
    }

    private int getSaturatedFat(String text) {
        String [] fields = text.split("g");
        return Integer.parseInt(fields[0]);
    }

    private int getSugars(String text) {
        String [] fields = text.split("g");
        return Integer.parseInt(fields[0]);
    }

    protected List<Recipe> process(Document doc) {
        List<Recipe> results = new ArrayList<>();

        Recipe recipe = new Recipe();

        recipe.title = cleanUpTitle(doc.title());
        recipe.scraper.description = this.NAME;

        // description + steps
        Elements directions = doc.select(".grouped-list");
        recipe.description =  doc.select("p").get(0).text();

        Elements ingredients = doc.select(".pb-xxs");

        Elements ingredientsHeadings = doc.select(".recipe__ingredients h3"); //ingredients titles
        Elements ingredientsSublist = doc.select(".pb-xxs");
        //System.out.println("ingrrr "+ ingredientsHeadings.get(1).text());
        //System.out.println("ingre list "+ingredientsSublist.get(0).text());
        //String [] ingredientsList = ingredients2.get(0).text().split("For");

        int i = 0;

        String [] steps = directions.get(0).text().split("STEP ");

        steps = removeFirstXElements(steps,1);

        for (String step : steps){
            step = step.substring(2);
            recipe.steps.add(new Step(step , ++i));
        }

        IngredientGroup defaultGroup = new IngredientGroup();

        recipe.ingredientGroups.add(defaultGroup);

        for (Element ingredient : ingredients){
            defaultGroup.ingredients.add(new Ingredient(ingredient.text()));
        }

        Elements info = doc.select(".icon-with-text__children");

        recipe.preparation_time =  getPreparationTime(info.get(0).text());
        recipe.cooking_time =  getCookingTime(info.get(0).text());

        recipe.difficulty.description = getDifficultyLevel(info.get(1).text());

        recipe.servings  = info.get(2).text();


        Elements nutrition = doc.select(".key-value-blocks");
        System.out.println("nutr info: "+nutrition.get(0).text());
        String[] nutritionInfo = splitNutritionInfo(nutrition.get(0).text());
        recipe.nutritionInfo.kcal = getCalories(nutritionInfo[6]);
        recipe.nutritionInfo.protein = getProtein(nutritionInfo[18]);
        recipe.nutritionInfo.fats = getFat(nutritionInfo[8]);
        recipe.nutritionInfo.carbohydrates = getCarbohydrates(nutritionInfo[12]);
        recipe.nutritionInfo.saturated_fat = getSaturatedFat(nutritionInfo[10]);
        recipe.nutritionInfo.sugars = getSugars(nutritionInfo[14]);



        // category

        results.add(recipe);

        return results;
    }
}