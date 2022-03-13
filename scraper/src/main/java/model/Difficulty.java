package model;

public class Difficulty {
    public long id;
    public String description = "undefined";

    @Override
    public String toString() {
        return description;
    }
}

