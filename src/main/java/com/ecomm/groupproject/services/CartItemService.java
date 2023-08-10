package com.ecomm.groupproject.services;

import com.ecomm.groupproject.models.CartItem;
import com.ecomm.groupproject.models.Product;
import com.ecomm.groupproject.repositories.CartItemRepository;
import com.ecomm.groupproject.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productRepository;


    //get CartItems
    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }
    public CartItem getCartItemById(Long id) {
        return cartItemRepository.findById(id).orElse(null);
    }
    public CartItem getCartItemByProductId(Long productId) {
        return cartItemRepository.findByProductId(productId);
    }


    //CREATE
    public CartItem addNewCartItem(CartItem cartItem) {
        cartItem.setQuantity(1);
        return cartItemRepository.save(cartItem);
    }

    //DELETE
    public void deleteThisCartItem(Long id){
        cartItemRepository.deleteById(id);
    }


    public List<CartItem> getCartItemsByUserId(Long loggedInUserId) {
        return cartItemRepository.findByShoppingCartId(loggedInUserId);
    }
}
