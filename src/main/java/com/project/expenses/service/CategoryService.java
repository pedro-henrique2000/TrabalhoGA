package com.project.expenses.service;

import com.project.expenses.domain.Category;
import com.project.expenses.repository.JpaCategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final JpaCategoryRepository repository;

    public CategoryService(JpaCategoryRepository repository) {
        this.repository = repository;
    }

    public Long saveCategory(String name) {
        try {
            var saved = repository.save(new Category(name));
            return saved.getId();
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
            return null;
        }
    }

    public Category findById(Long id) {
        return repository.findById(id).orElse(null);
    }

}
