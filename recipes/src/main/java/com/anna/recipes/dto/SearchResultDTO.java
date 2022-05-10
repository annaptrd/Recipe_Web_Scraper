package com.anna.recipes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SearchResultDTO {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public class Statistics {
        int total;
    }

    private Statistics metadata = new Statistics();
    private List<RecipeDTO> recipes;

}
