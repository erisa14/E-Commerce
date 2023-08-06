package com.ecomm.groupproject.services;

import com.ecomm.groupproject.models.Category;
import com.ecomm.groupproject.models.Product;
import com.ecomm.groupproject.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getByCategory(Category category){
        return productRepository.findByCategory(category);
    }


    //get all products  --  by joana
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public List<Product> getByCategoryName(Category category){
        return productRepository.findByCategory(category);
    }

}
