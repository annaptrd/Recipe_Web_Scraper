package com.anna.recipes.mapper;

import com.anna.recipes.dto.IngredientGroupDTO;
import com.anna.recipes.model.Ingredient;
import com.anna.recipes.model.IngredientGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientGroupMapper {
    @Autowired
    IngredientMapper ingredientMapper;

    public IngredientGroupDTO toDTO(IngredientGroup entity) {
        IngredientGroupDTO dto = new IngredientGroupDTO();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());

        dto.setIgredients( new ArrayList<>());

        for (Ingredient i : entity.getIngredientList()) {
            dto.getIgredients().add(ingredientMapper.toDTO(i));
        }
        return dto;
    }

    public List<IngredientGroupDTO> toDTO(List<IngredientGroup> entities) {
        List<IngredientGroupDTO> dtos = new ArrayList<>();

        for (IngredientGroup c : entities) {
            dtos.add(toDTO(c));
        }
        return dtos;
    }
}
