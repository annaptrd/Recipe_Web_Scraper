package com.anna.recipes.service;


import com.anna.recipes.database.NutritionInfoDAO;
import com.anna.recipes.model.NutritionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NutritionInfoService {
    @Autowired
    NutritionInfoDAO dao;

    public Optional<NutritionInfo> findById(int id) {
        return dao.findById(id);
    }

    public List<NutritionInfo> list() {
        return dao.findAll();
    }
}
