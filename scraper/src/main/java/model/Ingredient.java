package model;

import org.apache.commons.codec.digest.DigestUtils;

public class Ingredient {
    public long id;
    public String text = "undefined";
    public String hash_value = DigestUtils.md5Hex(text).toUpperCase();

    public Ingredient(String text) {
        this.text = text;
        this.hash_value = DigestUtils.md5Hex(text).toUpperCase();
    }
}
