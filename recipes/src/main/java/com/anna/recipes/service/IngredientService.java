package com.anna.recipes.service;


import com.anna.recipes.database.IngredientDAO;
import com.anna.recipes.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {
    @Autowired
    IngredientDAO dao;

    public Optional<Ingredient> findById(int id) {
        return dao.findById(id);
    }

    public List<Ingredient> list() {
        return dao.findAll();
    }
}
