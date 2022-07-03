package scraper;

import model.Ingredient;
import model.IngredientGroup;
import model.Recipe;
import model.Step;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllRecipesScraper extends BaseScraper {

    public AllRecipesScraper() {
        super("AllRecipes Scraper");
    }

    private String cleanUpTitle(String title) {
        String newTitle = title.replace(" | Allrecipes", "");
        return newTitle.replace("Recipe", "");
    }

    private String cleanUpStep(String step) {
        String newStep = step.replace("Advertisement", "");
        return newStep;
    }

    public String removeFirstXElements(String text, int x) {
//        String newText = new String[text.substring(1)];
//        for (int i = 1; i < arr.length; i++) {
//            newArr[i-1] = arr[i];
//        }
        return text.substring(x);
    }

    protected List<Recipe> process(Document doc) {
        List<Recipe> results = new ArrayList<>();

        Recipe recipe = new Recipe();

        recipe.title = cleanUpTitle(doc.title());

        recipe.scraper.description = this.NAME;

        // TODO:
        recipe.photo_url = "to be determined";


        Elements directions = doc.select(".instructions-section-item");


        int i = 0;

        String step;
        for (Element steps : directions) {

            step = removeFirstXElements(steps.text(), 6);
            if (step.contains("Advertisement")) {
                step = cleanUpStep(step);
            }
            recipe.steps.add(new Step(step, ++i));
        }
        Elements writer = doc.select(".author-name");
        recipe.writer = writer.text();

        recipe.description = doc.select("p").get(0).text();

        Elements category = doc.select(".breadcrumbs__title");

        recipe.category.description = category.get(2).text();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Elements recipe_info = doc.select(".recipe-info-section");
        String[] recipeInfo = recipe_info.text().split(" ");
//        recipe.cooking_time = Integer.parseInt(recipeInfo[3]);
//        recipe.preparation_time = Integer.parseInt(recipeInfo[9]);
//        recipe.servings = recipeInfo[12];

        Elements recipe_info2 = doc.select(".recipe-meta-item");
        String recipe_info_temp;
        boolean prep_exists = false;
        boolean cook_exists = false;
        boolean servings_exists = false;
        boolean total_exists = false;
        int count_servings = 0;
        int count = 0;
        for (Element rec_info : recipe_info2) {
            if (rec_info.text().contains("cook")) {
                cook_exists = true;
            }
            if (rec_info.text().contains("prep")) {
                prep_exists = true;
            }
            if (rec_info.text().contains("total")) {
                total_exists = true;
            }
            if (rec_info.text().contains("Servings")) {
                servings_exists = true;
                count_servings = count;
            }
            count++;
        }
        int count_exists = 0;


        String[] recipe_info_temp2;
        if (cook_exists == true) {
            count_exists++;
            if (recipe_info2.get(0).text().contains("cook")) {
                recipe_info_temp = recipe_info2.get(0).text().replace("cook: ", "");
            } else {
                recipe_info_temp = recipe_info2.get(0).text().replace("total: ", "");
            }

            recipe_info_temp2 = recipe_info_temp.split(" ");

            if (recipe_info_temp2[1].equals("mins")) {
                recipe.cooking_time = Integer.parseInt(recipe_info_temp2[0]);
            } else {

                if (recipe_info_temp2.length > 2) {
                    recipe.cooking_time = Integer.parseInt(recipe_info_temp2[0]) * 60 + Integer.parseInt(recipe_info_temp2[2]);
                } else {
                    recipe.cooking_time = Integer.parseInt(recipe_info_temp2[0]) * 60;
                }
            }
        } else {
            recipe.cooking_time = 0;
        }
        if (prep_exists == true) {
            count_exists++;
            if (recipe_info2.get(1).text().contains("prep")) {
                recipe_info_temp = recipe_info2.get(1).text().replace("prep: ", "");
            } else if (recipe_info2.get(2).text().contains("prep")) {
                recipe_info_temp = recipe_info2.get(2).text().replace("prep: ", "");
            } else if (recipe_info2.get(3).text().contains("prep")) {
                recipe_info_temp = recipe_info2.get(3).text().replace("prep: ", "");
            } else {
                recipe_info_temp = recipe_info2.get(4).text().replace("prep: ", "");
            }
            recipe_info_temp2 = recipe_info_temp.split(" ");
            System.out.println(recipe_info_temp2[1]);
            if (recipe_info_temp2[1].equals("mins")) {
                recipe.preparation_time = Integer.parseInt(recipe_info_temp2[0]);
            } else {

                if (recipe_info_temp2.length > 2) {
                    recipe.preparation_time = Integer.parseInt(recipe_info_temp2[0]) * 60 + Integer.parseInt(recipe_info_temp2[2]);
                } else {
                    recipe.preparation_time = Integer.parseInt(recipe_info_temp2[0]) * 60;
                }
            }
        } else {
            recipe.preparation_time = 0;
        }
        if (servings_exists == true) {
            count_exists++;
            System.out.println(recipe_info2.get(count_servings).text());
            recipe_info_temp = recipe_info2.get(count_servings).text().replace("Servings: ", "");
            recipe.servings = recipe_info_temp;
        } else {
            recipe.servings = "Undefined";
        }


        long scraped_at = timestamp.getTime();
        recipe.scraped_at = timestamp;
        if (recipe.cooking_time > 90) {
            recipe.difficulty.description = "Hard";
        } else if (recipe.cooking_time > 45) {
            recipe.difficulty.description = "Medium";
        } else {
            recipe.difficulty.description = "Easy";
        }

        Elements nutritionInfo = doc.select(".recipeNutritionSectionBlock");
        String[] nutr = nutritionInfo.text().split(" ");
        System.out.println(nutritionInfo.text());
        recipe.nutritionInfo.kcal = Integer.parseInt(nutr[2]);
        String nutrition_temp_string = nutr[5].replace("g;", "");
        recipe.nutritionInfo.protein = Float.parseFloat(nutrition_temp_string);
        nutrition_temp_string = nutr[7].replace("g;", "");
        recipe.nutritionInfo.carbohydrates = Float.parseFloat(nutrition_temp_string);
        nutrition_temp_string = nutr[9].replace("g;", "");
        recipe.nutritionInfo.fat = Float.parseFloat(nutrition_temp_string);
        nutrition_temp_string = nutr[7].replace("g;", "");
        recipe.nutritionInfo.carbohydrates = Float.parseFloat(nutrition_temp_string);

        Elements nutrition_info = doc.select(".nutrition-row");
        nutrition_temp_string = nutrition_info.get(5).text().replace("saturated fat: ", "");
        String[] sat_fat = nutrition_temp_string.split("g");
        recipe.nutritionInfo.saturated_fat = Float.parseFloat(sat_fat[0]);
        nutrition_temp_string = nutrition_info.get(3).text().replace("sugars: ", "");
        String[] sugars = nutrition_temp_string.split("g");
        recipe.nutritionInfo.sugars = Float.parseFloat(sugars[0]);

        Elements ingredients = doc.select(".ingredients-item-name");
        recipe.number_of_ingredients = ingredients.size();
        IngredientGroup defaultGroup = new IngredientGroup(0, "Generic");
        recipe.ingredientGroups.add(defaultGroup);

        Set<String> ingredientSet = new HashSet<>();

        for (Element ingredient : ingredients) {
            ingredientSet.add(ingredient.text());
        }

        for (String str : ingredientSet) {
            defaultGroup.ingredients.add(new Ingredient(str));
        }

        recipe.uri = doc.baseUri();
        results.add(recipe);

        return results;
    }
}