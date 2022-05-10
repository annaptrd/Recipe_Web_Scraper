import crawler.Importer;
import database.Database;
import model.Recipe;
import repository.RecipeRepository;
import scraper.AllRecipesScraper;
import scraper.BbcGoodFoodScraper;
import scraper.ScraperInterface;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;


// #1 read urls from files
// #2 insert results to database
// #3 implement scrapers

public class Main {
    public static void main(String [] args) {
        ScraperInterface allRecipesScraper = new AllRecipesScraper();
        ScraperInterface bbcGoodFoodScraper = new BbcGoodFoodScraper();
        Importer importer = new Importer();
        Database db = new Database(false, false);
        RecipeRepository repository = new RecipeRepository(db);

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


            int counter = 0;
            int failed = 0;

            for (Map.Entry<String, ScraperInterface> job : jobs.entrySet()) {
                String url = job.getKey();
                ScraperInterface scraper = job.getValue();

                List<Recipe> results = scraper.scrape(url);

                for (Recipe r : results) {
//                    System.out.println("============================================================================");

//                    r.print();

                    db.newTransaction();

                    try {
                        repository.insert(r);

                        db.commit();
                        counter++;
                    } catch (Exception ex ) {
                        db.rollback();
                        System.out.println("[ *** Warning *** ] Recipe could not be inserted: " + ex.getMessage());
                        failed++;
                    }
                }
            }

            System.out.println("Imported: "  + counter);
            System.out.println("Failed  : " + failed);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
