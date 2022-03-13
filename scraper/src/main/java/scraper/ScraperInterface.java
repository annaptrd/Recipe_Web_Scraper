package scraper;

import model.Recipe;

import java.io.IOException;
import java.util.List;

public interface ScraperInterface {
    public List<Recipe> scrape(List<String> urls) throws IOException;
    public List<Recipe> scrape(String url) throws IOException;
}
