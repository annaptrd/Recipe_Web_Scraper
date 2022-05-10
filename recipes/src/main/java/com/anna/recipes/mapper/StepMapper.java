package com.anna.recipes.mapper;

import com.anna.recipes.dto.StepDTO;
import com.anna.recipes.model.Step;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StepMapper {
    public StepDTO toDTO(Step entity) {
        StepDTO dto = new StepDTO();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setOrder(entity.getOrder());
        return dto;
    }

    public List<StepDTO> toDTO(List<Step> entities) {
        List<StepDTO> dtos = new ArrayList<>();

        for (Step c : entities) {
            dtos.add(toDTO(c));
        }
        return dtos;
    }
}
