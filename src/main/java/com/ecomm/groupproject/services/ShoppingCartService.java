package com.ecomm.groupproject.services;

import com.ecomm.groupproject.models.CartItem;
import com.ecomm.groupproject.models.ShoppingCart;
import com.ecomm.groupproject.models.User;
import com.ecomm.groupproject.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private UserService userService;


    //GET THIS SHOPPING CART  by ___
    public ShoppingCart getShoppingCartById(Long id) {
        return shoppingCartRepository.findById(id).orElse(null);
    }
    public Long getShoppingCartIdByUserId(Long userId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId);
        if (shoppingCart != null) {
            return shoppingCart.getId();
        }
        return null; // Return null or throw an exception if shopping cart doesn't exist
    }
    public ShoppingCart getShoppingCartByUser(User user) {
        return shoppingCartRepository.findByUserId(user.getId());
    }


    //CREATE
    public ShoppingCart createNewShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart updateThisShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    //DELETE
    public void deleteThisShoppingCart(Long id){
        shoppingCartRepository.deleteById(id);
    }


}
