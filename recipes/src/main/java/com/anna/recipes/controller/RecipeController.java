package com.anna.recipes.controller;

import com.anna.recipes.dto.RecipeDTO;
import com.anna.recipes.dto.SearchDTO;
import com.anna.recipes.dto.SearchResultDTO;
import com.anna.recipes.exceptions.EntityNotFoundException;
import com.anna.recipes.mapper.RecipeMapper;
import com.anna.recipes.mapper.SearchResultMapper;
import com.anna.recipes.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RecipeController {
    @Autowired
    RecipeService service;

    @Autowired
    RecipeMapper mapper;

    @Autowired
    SearchResultMapper searchMapper;

    @GetMapping(value="/recipe/{id}")
    public RecipeDTO getRecipeByID(@PathVariable("id") int id){
        return mapper.toDTO(service.findById(id).orElseThrow(()->new EntityNotFoundException("Recipe")));
    }

    @GetMapping(value="/recipe/similar/{id}")
    public List<RecipeDTO> getSimilarRecipesByID(@PathVariable("id") int id){
        return mapper.toDTO(service.findSimilarById(id));
    }

    @GetMapping(value="/recipe/all")
    public List<RecipeDTO> getAll(){
        return mapper.toDTO(service.list());
    }

    @GetMapping(value="/recipe/latest")
    public List<RecipeDTO> getLatest(){
        return mapper.toDTO(service.latest());
    }


    @PostMapping(value="/recipe/search")
    public SearchResultDTO search(@RequestBody SearchDTO input){
        return searchMapper.toDTO(service.search(input));
    }

//    @PostMapping(value="/recipe/search")
//    public SearchDTO search(@RequestBody SearchDTO input){
//        return input;// mapper.toDTO(service.search(input));
//    }
}
