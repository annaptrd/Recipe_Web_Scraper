package com.anna.recipes.dto;

import com.anna.recipes.model.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IngredientGroupDTO {
    private Integer id;
    private String description;
    private List<IngredientDTO> igredients;
}
