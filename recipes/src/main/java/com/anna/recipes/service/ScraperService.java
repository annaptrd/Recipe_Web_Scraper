package com.anna.recipes.service;


import com.anna.recipes.database.ScraperDAO;
import com.anna.recipes.model.Scraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScraperService {
    @Autowired
    ScraperDAO dao;

    public Optional<Scraper> findById(int id) {
        return dao.findById(id);
    }

    public List<Scraper> list() {
        return dao.findAll();
    }
}
