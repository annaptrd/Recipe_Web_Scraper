package com.anna.recipes.service;


import com.anna.recipes.database.CategoryDAO;
import com.anna.recipes.database.RecipeDAO;
import com.anna.recipes.model.Category;
import com.anna.recipes.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryDAO dao;

    public Optional<Category> findById(int id) {
        return dao.findById(id);
    }

    public List<Category> list() {
        return dao.findAll();
    }
}
