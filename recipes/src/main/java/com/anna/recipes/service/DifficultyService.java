package com.anna.recipes.service;


import com.anna.recipes.database.DifficultyDAO;
import com.anna.recipes.model.Difficulty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DifficultyService {
    @Autowired
    DifficultyDAO dao;

    public Optional<Difficulty> findById(int id) {
        return dao.findById(id);
    }

    public List<Difficulty> list() {
        return dao.findAll();
    }
}
