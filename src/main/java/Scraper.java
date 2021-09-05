import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

public class Scraper {
    protected String description;
    protected String title;
    protected int stepsNumber;
    protected List<String> stepsList = new ArrayList<>();
    protected String nutritionInfo;
    protected List<String> ingredientsList = new ArrayList<>();
    protected String nutritionFacts;
    protected Document doc = null;


    public static void main(String[] args) {
        AllRecipesScraper allRecipesScraping = new AllRecipesScraper();
        //allRecipesScraping.scraping();

        FoodNetworkScraper foodNetworkScraping = new FoodNetworkScraper();
        foodNetworkScraping.scraping();
    }
}