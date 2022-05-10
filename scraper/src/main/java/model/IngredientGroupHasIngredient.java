package model;

public class IngredientGroupHasIngredient {
    public long ingredient_group_id;
    public long ingredient_id;

    public IngredientGroupHasIngredient(){

    }
    public IngredientGroupHasIngredient(long ingredient_group_id, long ingredient_id){
        this.ingredient_group_id = ingredient_group_id;
        this.ingredient_id = ingredient_id;
    }
}

