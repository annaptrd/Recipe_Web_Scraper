package com.anna.recipes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SearchDTO {
    private Optional<String> title = Optional.empty();
    private Optional<Integer> totalIngredients = Optional.empty();
    private List<String> ingredients = new ArrayList<>();
    private List<String> exclude_ingredients = new ArrayList<>();
    private Optional<Float> calories = Optional.empty();
    private Optional<String> writer = Optional.empty();
    private Optional<String> servings = Optional.empty();
    private Optional<Float>  time = Optional.empty();
    private Optional<String> difficulty = Optional.empty();
    private List<String> categories= new ArrayList<>();

    private Optional<Date> date_min = Optional.empty();
    private Optional<Date> date_max = Optional.empty();
}
