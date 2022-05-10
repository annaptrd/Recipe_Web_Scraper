package com.anna.recipes.mapper;

import com.anna.recipes.dto.RecipeDTO;
import com.anna.recipes.dto.SearchResultDTO;
import com.anna.recipes.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchResultMapper {
    @Autowired
    RecipeMapper mapper;


    public SearchResultDTO toDTO(List<Recipe> entities) {
        SearchResultDTO dto = new SearchResultDTO();

        dto.getMetadata().setTotal(entities.size());

        dto.setRecipes(mapper.toDTO(entities));


        return dto;
    }
}
