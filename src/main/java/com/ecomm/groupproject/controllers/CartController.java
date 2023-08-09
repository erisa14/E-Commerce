package com.ecomm.groupproject.controllers;

import com.ecomm.groupproject.models.*;
import com.ecomm.groupproject.services.CartItemService;
import com.ecomm.groupproject.services.ProductService;
import com.ecomm.groupproject.services.ShoppingCartService;
import com.ecomm.groupproject.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Controller
public class CartController {
    @Autowired
    private UserService userService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private ProductService productService;



    // ADD CART ITEM
//    @PostMapping("/new_cart_item")
//    public String addNewCartItem(@Valid @ModelAttribute("newCartItem") CartItem newCartItem, BindingResult result, HttpSession session) {
//        Long userId = (Long) session.getAttribute("loggedInUserId");
//        if (userId == null){
//            return "redirect:/";
//        }
//        User user = userService.findUserById(userId);   // marrim user-in
//        ShoppingCart shoppingCart = user.getShoppingCart();   // marrim shoppingCart-in
//        newCartItem.setShoppingCart(shoppingCart);     //marrim te dhenat nga shoppingCart
//
//        cartItemService.addNewCartItem(newCartItem);    // shtojme nje cart item
//        return "redirect:/users/home";
//    }


    @PostMapping("/new_cart_item")
    public String addNewCartItem(@Valid @ModelAttribute("newCartItem") CartItem newCartItem, BindingResult result,
                                 @RequestParam("productId") Long productId,
                                 HttpSession session) {
        Long userId = (Long) session.getAttribute("loggedInUserId");
        if (userId == null){
            return "redirect:/";
        }

        User user = userService.findUserById(userId);
        ShoppingCart shoppingCart = user.getShoppingCart();

        // Create a new Product instance using the retrieved values
        Product product = new Product();
        product.setId(productId);

        // Set the Product instance to the newCartItem
        newCartItem.setProduct(product);

        // Set the ShoppingCart and other necessary fields
        newCartItem.setShoppingCart(shoppingCart);

        // Add the newCartItem to the cart
        cartItemService.addNewCartItem(newCartItem);

        return "redirect:/users/home";
    }








    // DELETE CART ITEM
//    @DeleteMapping("/cart_item/{id}/delete")
//    public String deleteThisCartItem(@PathVariable("id") Long id, HttpSession session) {
//        Long userId = (Long) session.getAttribute("loggedInUserId");
//        if (userId == null){
//            return "redirect:/";
//        }
//        cartItemService.deleteThisCartItem(id);
//        return "redirect:/home";
//    }






    @GetMapping("/viewWishlist")
    public String viewWishlist(HttpSession session, Model model){
        Long userId = (Long) session.getAttribute("loggedInUserId");
        if (userId == null){
            return "redirect:/";
        }
        User user = userService.findUserById(userId);
        model.addAttribute("user", user);
        return "userViewWishlist";
    }




}