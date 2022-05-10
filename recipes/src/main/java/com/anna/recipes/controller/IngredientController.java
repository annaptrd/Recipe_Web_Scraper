package com.anna.recipes.controller;

import com.anna.recipes.dto.IngredientDTO;
import com.anna.recipes.exceptions.EntityNotFoundException;
import com.anna.recipes.mapper.IngredientMapper;
import com.anna.recipes.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class IngredientController {
    @Autowired
    IngredientService service;

    @Autowired
    IngredientMapper mapper;

    @GetMapping(value="/ingredient/{id}")
    public IngredientDTO getByID(@PathVariable("id") int id){
        return mapper.toDTO(service.findById(id).orElseThrow(()->new EntityNotFoundException("Ingredient")));
    }

    @GetMapping(value="/ingredient/all")
    public List<IngredientDTO> getAll(){
        return mapper.toDTO(service.list());
    }


}
