//package scraper;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class FoodNetworkScraper extends BaseScraper {
//    protected List<String> elementsList = new ArrayList<>();
//    public void scraping(){
//
//        try {
//         //   doc = Jsoup.connect("https://foodnetwork.co.uk/recipes/tom-kerridges-bbq-meatball-and-mozzarella-sub/").get();
//            doc = Jsoup.connect("https://foodnetwork.co.uk/recipes/marzipan-pumpkin-truffles/").get();
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//
//        title = doc.title();
//        System.out.println("TITLE:\n"+title);
//
//
//
//        Elements directions = doc.select(".recipe-description>p");
//        //directions.remove(directions.remove(directions.size()-1));
//
//        //alternatively parse the recipe as full description:
//        // Elements directions = doc.select(".recipe-description");
//
//        System.out.println("STEPS:\n");
//        for (Element step : directions){
//            stepsList.add(step.description());
//            System.out.println(step.description()+"\n");
//        }
//
//
//
//        Element firstH4 = doc.select("h4").first(); // h4 einai ta headers gia tis katigories twn ylikwn
//            if(firstH4!=null){
//                Elements siblings = firstH4.siblingElements();
////                System.out.println("!!!!"+siblings+"\n");
//                String ingredientsHeader;
//                ingredientsHeader = "\n"+firstH4.description()+":\n";
//                ingredientsList.add(ingredientsHeader);
//                List<Element> elementsBetween = new ArrayList<Element>();
//
//                for (int i = 1; i < siblings.size(); i++) {
//                    Element sibling = siblings.get(i);
//                    if (! "h4".equals(sibling.tagName())) {
//                        elementsBetween.add(sibling);
//                    }else {
//
//                        for (Element element : elementsBetween) {
//                            ingredientsList.add(element.description());
//                        }
//                        ingredientsHeader = "\n"+sibling.description()+":\n";
//                        ingredientsList.add(ingredientsHeader);
//                        elementsBetween.clear();
//                    }
//                }
//                if (! elementsBetween.isEmpty()){
//                    for (Element element : elementsBetween) {
//                        ingredientsList.add(element.description());
//                }}}
//        else{
//            Elements ingredientsElements = doc.select(".ingredient");
//            for (Element ingredient : ingredientsElements){
//                ingredientsList.add(ingredient.description()+"\n");
//        }
//
//    }
//
//
//System.out.println("INGREDIENTS\n"+ingredientsList+"\n");
//
////        for (Element ingredient : ingredientsElements){
////            ingredientsList.add(ingredient.description()+"\n");
////        }
////        for (Element element : elements){
////            elementsList.add(element.description()+"\n");
////        }
//
//}}