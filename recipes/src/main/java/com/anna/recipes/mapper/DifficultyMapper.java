package com.anna.recipes.mapper;

import com.anna.recipes.dto.DifficultyDTO;
import com.anna.recipes.model.Difficulty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DifficultyMapper {
    public DifficultyDTO toDTO(Difficulty entity) {
        DifficultyDTO dto = new DifficultyDTO();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public List<DifficultyDTO> toDTO(List<Difficulty> entities) {
        List<DifficultyDTO> dtos = new ArrayList<>();

        for (Difficulty c : entities) {
            dtos.add(toDTO(c));
        }
        return dtos;
    }
}
