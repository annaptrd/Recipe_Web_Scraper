package model;

public class Category {
    public long id;
    public String description = "undefined";

    public Category() {

    }

    public Category(String description) {
        this.description = description;
    }

    public Category(long id, String description) {
        this.id = id;
        this.description = description;
    }



    @Override
    public String toString() {
        return description;
    }
}
