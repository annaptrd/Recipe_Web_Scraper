package model;

public class Scraper {
    public long id;
    public String description= "undefined";

    public Scraper() {
    }

    public Scraper(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return description;
    }
}
