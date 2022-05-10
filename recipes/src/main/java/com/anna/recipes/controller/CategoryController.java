package com.anna.recipes.controller;

import com.anna.recipes.dto.CategoryDTO;
import com.anna.recipes.exceptions.EntityNotFoundException;
import com.anna.recipes.mapper.CategoryMapper;
import com.anna.recipes.model.Category;
import com.anna.recipes.model.Recipe;
import com.anna.recipes.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    CategoryService service;

    @Autowired
    CategoryMapper mapper;

    @GetMapping(value="/category/{id}")
    public CategoryDTO getByID(@PathVariable("id") int id){
        return mapper.toDTO(service.findById(id).orElseThrow(()->new EntityNotFoundException("Category")));
    }

    @GetMapping(value="/category/all")
    public List<CategoryDTO> getAll(){
        return mapper.toDTO(service.list());
    }


}
