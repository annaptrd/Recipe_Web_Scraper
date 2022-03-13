import crawler.Importer;
import database.Database;
import model.Recipe;
import scraper.AllRecipesScraper;
import scraper.BbcGoodFoodScraper;
import scraper.ScraperInterface;

import java.util.*;


// #1 read urls from files
// #2 insert results to database
// #3 implement scrapers

public class Main {
    public static void main(String [] args) {
        ScraperInterface allRecipesScraper = new AllRecipesScraper();
        ScraperInterface bbcGoodFoodScraper = new BbcGoodFoodScraper();
        Importer importer = new Importer();
        Database db = new Database();

        TreeMap<String, ScraperInterface> jobs = new TreeMap<>();

        try {
            List<String> allRecipeUrls = importer.loadFromResource("allrecipes.txt");
            List<String> bbcgoodfoodUrls = importer.loadFromResource("bbcgoodfood.txt");

            for (String url : allRecipeUrls) {
                jobs.put(url, allRecipesScraper);
            }

            for (String url : bbcgoodfoodUrls) {
                jobs.put(url, bbcGoodFoodScraper);

            }

            db.connect();

            for (Map.Entry<String, ScraperInterface> job : jobs.entrySet()) {
                String url = job.getKey();
                ScraperInterface scraper = job.getValue();

                List<Recipe> results = scraper.scrape(url);

                for (Recipe r : results) {
                    System.out.println("============================================================================");

                    r.print();
                }
            }


            db.disconnect();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
