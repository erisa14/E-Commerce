package com.ecomm.groupproject.services;

import com.ecomm.groupproject.models.Category;
import com.ecomm.groupproject.models.Product;
import com.ecomm.groupproject.repositories.ProductRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public List<Product> getByCategoryName(Category category){
        return productRepository.findByCategory(category);
    }



    //for wishlist
    public void addToWishlist(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            product.setWishlist(true);
            productRepository.save(product);
        }
    }

    public void removeFromWishlist(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            product.setWishlist(false);
            productRepository.save(product);
        }
    }






    //for SEARCH
    public List<Product> searchProducts(String searchTerm) {
        Specification<Product> spec = (root, query, criteriaBuilder) -> {
            // Add search conditions here using criteriaBuilder
            String likeSearchTerm = "%" + searchTerm + "%";
            Predicate predicate = criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), likeSearchTerm),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), likeSearchTerm)
                    // Add more attributes to search here as needed
            );
            return predicate;
        };

        return productRepository.findAll(spec);
    }
}