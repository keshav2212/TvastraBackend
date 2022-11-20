package com.tvastra.gallery.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    public void save(String category) {
        categoryRepository.save(new Category(category));
    }

    public Category getById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
}
