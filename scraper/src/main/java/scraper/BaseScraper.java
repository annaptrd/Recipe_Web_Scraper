package scraper;

import model.Recipe;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseScraper implements ScraperInterface {
    protected boolean verbose = true;
    public final String NAME;

    public BaseScraper(String name) {
        this.NAME = name;
    }

    @Override
    public List<Recipe> scrape(List<String> urls) throws IOException {
        List<Recipe> results = new ArrayList<>();

        for (String uri : urls) {
            List<Recipe> data = scrape(uri);
            results.addAll(data);
        }

        return results;
    }

    @Override
    public List<Recipe> scrape(String url) throws IOException {
        List<Recipe> results = new ArrayList<>();

//        if (verbose) {
//            System.out.println("connecting to: " + url);
//        }

        Document doc = connect(url);

        if (verbose) {
            System.out.println("[" + NAME + "]: Processing: " + url);
        }

        List<Recipe> data = process(doc);

        results.addAll(data);

//        if (verbose) {
//            System.out.println("disconnecting from: " + url);
//        }

        disconnect(doc);

        return results;
    }


    protected Document connect(String uri) throws IOException {
        return Jsoup.connect(uri).get();
    }

    protected void disconnect(Document doc) throws IOException {
        doc = null;
    }


    protected abstract List<Recipe> process(Document doc);
}
