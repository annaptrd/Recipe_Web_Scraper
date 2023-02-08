package com.anna.recipes.controller;

import com.anna.recipes.dto.DifficultyDTO;
import com.anna.recipes.exceptions.EntityNotFoundException;
import com.anna.recipes.mapper.DifficultyMapper;
import com.anna.recipes.service.DifficultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DifficultyController {
    @Autowired
    DifficultyService service;

    @Autowired
    DifficultyMapper mapper;

    @GetMapping(value="/difficulty/{id}")
    public DifficultyDTO getByID(@PathVariable("id") int id){
        return mapper.toDTO(service.findById(id).orElseThrow(()->new EntityNotFoundException("Difficulty")));
    }
    @GetMapping(value="/difficulty/all")
    public List<DifficultyDTO> getAll(){
        return mapper.toDTO(service.list());
    }
}
