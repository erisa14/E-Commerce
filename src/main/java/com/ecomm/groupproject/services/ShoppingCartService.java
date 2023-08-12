package com.ecomm.groupproject.services;

import com.ecomm.groupproject.models.CartItem;
import com.ecomm.groupproject.models.ShoppingCart;
import com.ecomm.groupproject.repositories.CartItemRepository;
import com.ecomm.groupproject.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    public ShoppingCart getShoppingCartByUserId(Long id) {
        return shoppingCartRepository.findByUserId(id);
    }
}