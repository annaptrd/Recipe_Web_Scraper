package factories;

import model.Category;
import model.Scraper;

import java.util.ArrayList;
import java.util.List;

public class ScraperFactory extends BaseFactory {
    public static Scraper create() {
        return new Scraper(faker.lorem().sentence());
    }

    public static List<Scraper> create(int n) {
        List<Scraper> list = new ArrayList<>();

        for (int i=0;i<n;i++) {
            list.add(create());
        }

        return list;
    }
}
