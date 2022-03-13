package scraper;

import model.Recipe;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AllRecipesScraper extends BaseScraper {

    public AllRecipesScraper() {
        super("AllRecipe Scraper");
    }

    protected List<Recipe> process(Document doc) {
        List<Recipe> results = new ArrayList<>();


        //download page to experiment
//        File input = new File("/Users/anna_petridou/Documents/RecipeWebScraper/Recipes _ Allrecipes.html");
//        Document doc = null;
//        try {
//            doc = Jsoup.parse(input, "UTF-8", "");
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//
//        // recipe title
//        title = doc.title();
//        title = title.replace(" | Allrecipes","");
//
//        // description + steps
//        Elements directions = doc.getElementsByTag("p"); //the first element is the description
//
//        description = directions.get(0).description();
//
//        //get number of steps
//        stepsNumber = directions.size();
//
//
//        //remove unnecessary info + descriptions leaving only the actual steps
//        directions.remove(stepsNumber-1);
//        directions.remove(0);
//        System.out.println("DESCRIPTION:\n"+description+"\n");
//
//        //get steps in arraylist
//        System.out.println("STEPS:\n");
//        for (Element step : directions){
//            stepsList.add(step.description());
//            System.out.println(step.description()+"\n");
//        }
//
//        Elements nutritionInfoElements = doc.select(".two-subcol-content-wrapper");
//        nutritionInfo = nutritionInfoElements.description();
//        System.out.println("NUTRITION INFO:\n"+nutritionInfo+"\n");
//
//        //get ingredients
//        Elements ingredientsElements = doc.select(".ingredients-item-NAME");
//        for (Element ingredient : ingredientsElements){
//            ingredientsList.add(ingredient.description());
//        }
//
//        System.out.println("INGREDIENTS\n"+ingredientsList+"\n");
//
//        Elements nutritionFactsElements = doc.select(".nutrition-body");
//        nutritionFacts = nutritionFactsElements.description();
//        System.out.println("NUTRITION FACTS:\n"+nutritionFacts+"\n");

        return results;
    }
}