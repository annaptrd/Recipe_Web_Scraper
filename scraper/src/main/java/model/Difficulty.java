package model;

public class Difficulty {
    public long id;
    public String description = "undefined";

    public Difficulty() {
    }

    public Difficulty(String description) {
        this.description = description;
    }

    public Difficulty(long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Difficulty(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return description;
    }
}

