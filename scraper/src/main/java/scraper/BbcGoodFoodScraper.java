package scraper;

import model.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BbcGoodFoodScraper extends BaseScraper {

    public BbcGoodFoodScraper() {
        super("BBCGoodFood Scraper");
    }

    private String cleanUpTitle(String title) {
        String newTitle = title.replace(" | BBC Good Food", "");
        return newTitle.replace("recipe", "");
    }

    private String cleanUpNutritionInfo(String title) {
        return title.replace("low in ", "");
    }

    private String cleanUpWriter(String name) {
        return name.replace("By ", "");
    }

    public String[] removeFirstXElements(String[] arr, int x) {
        String newArr[] = new String[arr.length - x];
        for (int i = 1; i < arr.length; i++) {
            newArr[i - 1] = arr[i];
        }
        return newArr;
    }

    private int getPreparationTime(String time) {
//        String [] fields = time.split(" ");
        String[] prep;
        String[] prep_time;
        if (time.contains("Prep:")) {
            prep = time.split("Prep:");
            prep_time = prep[1].split(" ");
        } else if (time.contains("Total time")) {
            prep = time.split("Total time");
            prep_time = prep[1].split(" ");
        } else {
            prep_time = time.split(" ");
        }
        //System.out.println(prep_time[1]);
        if (prep_time[1].equals("hrs") || prep_time[1].equals("hr")) {
            if (time.contains("and")) {
                return Integer.parseInt(prep_time[0]) * 60 + Integer.parseInt(prep_time[3]);
            } else {
                return Integer.parseInt(prep_time[0]) * 60;
            }

        } else {
            return Integer.parseInt(prep_time[0]);

        }
    }

    private int getCookingTime(String time) {
//        String [] fields = time.split(" ");
//        String [] prep = fields[2].split(":");

        String[] cook;
        String[] cook_time;
        if (time.contains("Cook:")) {
            cook = time.split("Cook:");
            cook_time = cook[1].split(" ");
        } else if (time.contains("Total time")) {
            cook = time.split("Total time");
            cook_time = cook[1].split(" ");
        } else {
            cook_time = time.split(" ");
        }
        // System.out.println(cook_time[1]);
        if (cook_time[1].equals("hrs") || cook_time[1].equals("hr")) {
            if (time.contains("and")) {
                return Integer.parseInt(cook_time[0]) * 60 + Integer.parseInt(cook_time[3]);
            } else {
                return Integer.parseInt(cook_time[0]) * 60;
            }
        } else {
            return Integer.parseInt(cook_time[0]);
        }

//        return Integer.parseInt(prep[1]);
    }


    private String[] splitNutritionInfo(String info) {
        String[] fields = info.split(" ");
        return fields;
    }

    private int getProtein(String text) {
        String[] fields = text.split("g");
        return Integer.parseInt(fields[0]);
    }

    private int getFat(String text) {
        String[] fields = text.split("g");
        return Integer.parseInt(fields[0]);
    }

    private int getCarbohydrates(String text) {
        String[] fields = text.split("g");
        return Integer.parseInt(fields[0]);
    }

    private int getCalories(String text) {
        String[] fields = text.split("g");
        return Integer.parseInt(fields[0]);
    }

    private int getSaturatedFat(String text) {
        String[] fields = text.split("g");
        return Integer.parseInt(fields[0]);
    }

    private int getSugars(String text) {
        String[] fields = text.split("g");
        return Integer.parseInt(fields[0]);
    }

    private String getDifficultyLevel(String text) {
        return text;
    }

    protected List<Recipe> process(Document doc) {
        List<Recipe> results = new ArrayList<>();

        Recipe recipe = new Recipe();

        recipe.title = cleanUpTitle(doc.title());
        recipe.scraper.description = this.NAME;

        // TODO:
        recipe.photo_url = "to be determined";

        // description + steps
        Elements directions = doc.select(".grouped-list");
        recipe.description = doc.select("p").get(0).text();

        Elements ingredients = doc.select(".pb-xxs");
        recipe.number_of_ingredients = ingredients.size();

        //Elements ingredientsText = doc.select(".recipe__ingredients"); //ingredients titles
        Elements ingredientsHeadings = doc.select(".recipe__ingredients h3"); //ingredients titles
        //Elements ingredientsSublist = doc.select(".pb-xxs");
        Elements ingredientsSublist = doc.select(".recipe__ingredients section");

        Elements writer = doc.select(".d-inline");
//        System.out.println("writer= "+ writer.get(0).description());

        recipe.writer = cleanUpWriter(writer.get(0).text());
        int i = 0;

        String[] steps = directions.get(0).text().split("STEP ");

        steps = removeFirstXElements(steps, 1);

        for (String step : steps) {
            step = step.substring(2);
            recipe.steps.add(new Step(step, ++i));
        }

        IngredientGroup defaultGroup = new IngredientGroup(0, "Generic");


        recipe.ingredientGroups.add(defaultGroup);

        for (Element ingredientsHeading : ingredientsHeadings) {
            recipe.ingredientGroups.add(new IngredientGroup(ingredientsHeading.text()));
        }

        for (Element ingredientSublist : ingredientsSublist) {
            for (IngredientGroup ingredientGroup : recipe.ingredientGroups) {
                if (ingredientSublist.text().contains(ingredientGroup.description)) {
                    for (Element ingredient : ingredients) {
                        if (ingredientSublist.text().contains(ingredient.text())) {
                            ingredientGroup.ingredients.add(new Ingredient(ingredient.text()));
                        }
                    }
                }
            }
        }
        boolean exists = false;
        for (Element ingredient : ingredients) {
            for (IngredientGroup ingredientGroup : recipe.ingredientGroups) {
                for (Ingredient ingredient1 : ingredientGroup.ingredients) {
                    if (ingredient1.description.equals(ingredient.text())) {
                        exists = true;
                    }
                }
            }
            if (!exists) {
                defaultGroup.ingredients.add(new Ingredient(ingredient.text()));
            }
            exists = false;
        }

        Elements info = doc.select(".icon-with-text__children");

        //System.out.println(info.get(0).text());
        recipe.preparation_time = getPreparationTime(info.get(0).text());
        recipe.cooking_time = getCookingTime(info.get(0).text());


        if (getDifficultyLevel(info.get(1).text()).equals("More effort")) {
            recipe.difficulty.description = "Medium";
        } else if (getDifficultyLevel(info.get(1).text()).equals("A challenge")) {
            recipe.difficulty.description = "Hard";
        } else {
            recipe.difficulty.description = getDifficultyLevel(info.get(1).text());
        }
        recipe.servings = info.get(2).text();


        Elements nutrition = doc.select(".key-value-blocks");

        String nutr_info = nutrition.get(0).text();
        //System.out.println(nutr_info);

        String[] temp = nutr_info.split("kcal ");

        nutr_info = temp[1];
        nutr_info = cleanUpNutritionInfo(nutr_info);
        String[] nutritionInfo = splitNutritionInfo(nutr_info);
        recipe.nutritionInfo.kcal = getCalories(nutritionInfo[0]);
        recipe.nutritionInfo.protein = getProtein(nutritionInfo[12]);
        recipe.nutritionInfo.fat = getFat(nutritionInfo[2]);
        recipe.nutritionInfo.carbohydrates = getCarbohydrates(nutritionInfo[6]);
        recipe.nutritionInfo.saturated_fat = getSaturatedFat(nutritionInfo[4]);
        recipe.nutritionInfo.sugars = getSugars(nutritionInfo[8]);


        recipe.category.description = "Generic Recipes";

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        long scraped_at = timestamp.getTime();
        recipe.scraped_at = timestamp;

        recipe.uri = doc.baseUri();
        results.add(recipe);

        return results;
    }
}