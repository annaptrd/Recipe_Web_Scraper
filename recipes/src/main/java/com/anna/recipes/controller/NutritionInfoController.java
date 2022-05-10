package com.anna.recipes.controller;

import com.anna.recipes.dto.NutritionInfoDTO;
import com.anna.recipes.exceptions.EntityNotFoundException;
import com.anna.recipes.mapper.NutritionInfoMapper;
import com.anna.recipes.service.NutritionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NutritionInfoController {
    @Autowired
    NutritionInfoService service;

    @Autowired
    NutritionInfoMapper mapper;

    @GetMapping(value="/nutrition_info/{id}")
    public NutritionInfoDTO getByID(@PathVariable("id") int id){
        return mapper.toDTO(service.findById(id).orElseThrow(()->new EntityNotFoundException("NutritionInfo")));
    }

    @GetMapping(value="/nutrition_info/all")
    public List<NutritionInfoDTO> getAll(){
        return mapper.toDTO(service.list());
    }


}
