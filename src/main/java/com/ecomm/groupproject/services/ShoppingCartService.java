package com.ecomm.groupproject.services;

import com.ecomm.groupproject.models.ShoppingCart;
import com.ecomm.groupproject.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public ShoppingCart getShoppingCartById(Long id) {
        return shoppingCartRepository.findById(id).orElse(null);
    }
}


