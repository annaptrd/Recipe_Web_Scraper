import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class FoodNetworkScraper extends Scraper{
    public void scraping(){

        try {
            doc = Jsoup.connect("https://foodnetwork.co.uk/recipes/tom-kerridges-bbq-meatball-and-mozzarella-sub/").get();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        title = doc.title();
        System.out.println("TITLE:\n"+title);

        Elements directions = doc.getElementsByTag("p");
        directions.remove(directions.remove(directions.size()-1));


        System.out.println("STEPS:\n");
        for (Element step : directions){
            stepsList.add(step.text());
            System.out.println(step.text()+"\n");
        }


        Elements ingredientsElements = doc.select(".sticky-content");
        for (Element ingredient : ingredientsElements){
            ingredientsList.add(ingredient.text());
        }

        System.out.println("INGREDIENTS\n"+ingredientsList+"\n");

}}
