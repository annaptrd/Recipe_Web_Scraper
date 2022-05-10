package model;

import org.apache.commons.codec.digest.DigestUtils;

public class Ingredient {
    public long id;
    public String description = "undefined";
    public String hash_value = DigestUtils.md5Hex(description).toUpperCase();

    public Ingredient(){

    }
    public Ingredient(String text) {
        this.description = text;
        this.hash_value = DigestUtils.md5Hex(text).toUpperCase();
    }
}
