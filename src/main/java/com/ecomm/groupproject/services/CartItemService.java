package com.ecomm.groupproject.services;

import com.ecomm.groupproject.models.CartItem;
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
    public CartItem createNewCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }
    public CartItem updateThisCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }



    //DELETE
    public void deleteThisCartItem(Long id){
        cartItemRepository.deleteById(id);
    }


}
