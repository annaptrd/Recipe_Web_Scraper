package com.anna.recipes.mapper;

import com.anna.recipes.dto.IngredientDTO;
import com.anna.recipes.model.Ingredient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientMapper {
    public IngredientDTO toDTO(Ingredient entity) {
        IngredientDTO dto = new IngredientDTO();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setHash(entity.getHashValue());
        return dto;
    }

    public List<IngredientDTO> toDTO(List<Ingredient> entities) {
        List<IngredientDTO> dtos = new ArrayList<>();

        for (Ingredient c : entities) {
            dtos.add(toDTO(c));
        }
        return dtos;
    }
}
