package com.anna.recipes.mapper;

import com.anna.recipes.dto.NutritionInfoDTO;
import com.anna.recipes.model.NutritionInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NutritionInfoMapper {
    public NutritionInfoDTO toDTO(NutritionInfo entity) {
        NutritionInfoDTO dto = new NutritionInfoDTO();
        dto.setId(entity.getId());
        dto.setCalories(entity.getCalories());
        dto.setCarbohydrates(entity.getCarbohydrates());
        dto.setProtein(entity.getProtein());
        dto.setFat(entity.getFat());
        dto.setSaturated_fat(entity.getSaturatedFat());
        dto.setSugars(entity.getSugars());
        return dto;
    }

    public List<NutritionInfoDTO> toDTO(List<NutritionInfo> entities) {
        List<NutritionInfoDTO> dtos = new ArrayList<>();

        for (NutritionInfo c : entities) {
            dtos.add(toDTO(c));
        }
        return dtos;
    }
}
