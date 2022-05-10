package com.anna.recipes.controller;

import com.anna.recipes.dto.ScraperDTO;
import com.anna.recipes.exceptions.EntityNotFoundException;
import com.anna.recipes.mapper.ScraperMapper;
import com.anna.recipes.service.ScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ScraperController {
    @Autowired
    ScraperService service;

    @Autowired
    ScraperMapper mapper;

    @GetMapping(value="/scraper/{id}")
    public ScraperDTO getByID(@PathVariable("id") int id){
        return mapper.toDTO(service.findById(id).orElseThrow(()->new EntityNotFoundException("Scraper")));
    }

    @GetMapping(value="/scraper/all")
    public List<ScraperDTO> getAll(){
        return mapper.toDTO(service.list());
    }


}
