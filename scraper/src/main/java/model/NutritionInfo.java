package model;

public class NutritionInfo {
    public long id;
    public float protein = -1;
    public float fat = -1;
    public float carbohydrates = -1;
    public float kcal = -1;
    public float sugars = -1;
    public float saturated_fat = -1;

    public NutritionInfo(){

    }
    public NutritionInfo(long id, float protein, float fats, float carbohydrates, float kcal, float saturated_fat, float sugars) {
        this.id = id;
        this.protein = protein;
        this.fat = fats;
        this.carbohydrates = carbohydrates;
        this.kcal = kcal;
        this.saturated_fat = saturated_fat;
        this.sugars = sugars;
    }

    @Override
    public String toString() {
        return "NutritionInfo{" +
                "ingredient_group_id=" + id +
                ", protein=" + protein +
                ", fat=" + fat +
                ", carbohydrates=" + carbohydrates +
                ", kcal=" + kcal +
                ", sugars=" + sugars +
                ", saturated_fat=" + saturated_fat +
                '}';
    }
}