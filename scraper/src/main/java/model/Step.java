package model;

public class Step {
    public long id;
    public int order;
    public String text;
    public long recipe_id;

    public Step(String text){

    }
    public Step(){

    }
    public Step(String text, int order) {
        this.text = text;
        this.order = order;
    }

    @Override
    public String toString() {
        return order + ". " + text;
    }
}
