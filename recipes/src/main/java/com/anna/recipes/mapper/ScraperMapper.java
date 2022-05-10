package com.anna.recipes.mapper;

import com.anna.recipes.dto.ScraperDTO;
import com.anna.recipes.model.Scraper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScraperMapper {
    public ScraperDTO toDTO(Scraper entity) {
        ScraperDTO dto = new ScraperDTO();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public List<ScraperDTO> toDTO(List<Scraper> entities) {
        List<ScraperDTO> dtos = new ArrayList<>();

        for (Scraper c : entities) {
            dtos.add(toDTO(c));
        }
        return dtos;
    }
}
