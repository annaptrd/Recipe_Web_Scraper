import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class AllRecipesScraper extends Scraper{

    public void scraping(){

        try {
            // doc = Jsoup.connect("https://www.allrecipes.com/recipe/25037/best-big-fat-chewy-chocolate-chip-cookie/").get();
            doc = Jsoup.connect("https://www.allrecipes.com/recipe/19037/dessert-crepes/").get();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }


        //download page to experiment
//        File input = new File("/Users/anna_petridou/Documents/RecipeWebScraper/Recipes _ Allrecipes.html");
//        Document doc = null;
//        try {
//            doc = Jsoup.parse(input, "UTF-8", "");
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }

        // recipe title
        title = doc.title();

        // description + steps
        Elements directions = doc.getElementsByTag("p"); //the first element is the description

        description = directions.get(0).text();

        //get number of steps
        stepsNumber = directions.size();

        //remove unnecessary info + descriptions leaving only the actual steps
        directions.remove(stepsNumber-1);
        directions.remove(stepsNumber-2);
        directions.remove(0);

        System.out.println("TITLE:\n"+title+"\n");
        System.out.println("DESCRIPTION:\n"+description+"\n");

        //get steps in arraylist
        System.out.println("STEPS:\n");
        for (Element step : directions){
            stepsList.add(step.text());
            System.out.println(step.text()+"\n");
        }

        Elements nutritionInfoElements = doc.select(".two-subcol-content-wrapper");
        nutritionInfo = nutritionInfoElements.text();
        System.out.println("NUTRITION INFO:\n"+nutritionInfo+"\n");

        //get ingredients
        Elements ingredientsElements = doc.select(".ingredients-item-name");
        for (Element ingredient : ingredientsElements){
            ingredientsList.add(ingredient.text());
        }

        System.out.println("INGREDIENTS\n"+ingredientsList+"\n");

        Elements nutritionFactsElements = doc.select(".nutrition-body");
        nutritionFacts = nutritionFactsElements.text();
        System.out.println("NUTRITION FACTS:\n"+nutritionFacts+"\n");
    }
}
