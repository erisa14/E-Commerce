package com.ecomm.groupproject.services;

import com.ecomm.groupproject.models.Category;
import com.ecomm.groupproject.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll(){
        return (List<Category>) categoryRepository.findAll();
    }

    public Category getByName(String name){
        return this.categoryRepository.findByName(name).orElse(null);
    }

}