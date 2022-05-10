package com.anna.recipes.mapper;

import com.anna.recipes.dto.CategoryDTO;
import com.anna.recipes.model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryMapper {
    public CategoryDTO toDTO(Category entity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public List<CategoryDTO> toDTO(List<Category> entities) {
        List<CategoryDTO> dtos = new ArrayList<>();

        for (Category c : entities) {
            dtos.add(toDTO(c));
        }
        return dtos;
    }
}
