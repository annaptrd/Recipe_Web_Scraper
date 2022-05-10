package com.anna.recipes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NutritionInfoDTO {
    private Integer id;
    private Float calories;
    private Float carbohydrates;
    private Float protein;
    private Float fat;
    private Float saturated_fat;
    private Float sugars;

}
