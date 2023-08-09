package com.ecomm.groupproject.services;

import com.ecomm.groupproject.models.CartItem;
import com.ecomm.groupproject.models.Product;
import com.ecomm.groupproject.repositories.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;


    //get all CartItems
    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }
    public CartItem getCartItemById(Long id) {
        return cartItemRepository.findById(id).orElse(null);
    }

    //CREATE + EDIT
    public CartItem addNewCartItem(CartItem cartItem) {
        if(cartItem.getQuantity() == 0) {
            cartItem.setQuantity(1);
        }
        else {
            cartItem.setQuantity(cartItem.getQuantity()+1);
        }
        return cartItemRepository.save(cartItem);
    }
    public CartItem updateThisCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    public List<CartItem> getAllProducts() {
        return cartItemRepository.findAll();

    }


    //DELETE
    public void deleteThisCartItem(Long id){
        cartItemRepository.deleteById(id);
    }


    public List<CartItem> getCartItemsByUserId(Long loggedInUserId) {
        return cartItemRepository.findByShoppingCartId(loggedInUserId);
    }
}