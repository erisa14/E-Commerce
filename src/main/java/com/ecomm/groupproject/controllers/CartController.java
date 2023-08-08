package com.ecomm.groupproject.controllers;

import com.ecomm.groupproject.models.CartItem;
import com.ecomm.groupproject.models.Category;
import com.ecomm.groupproject.models.ShoppingCart;
import com.ecomm.groupproject.models.User;
import com.ecomm.groupproject.services.CartItemService;
import com.ecomm.groupproject.services.ShoppingCartService;
import com.ecomm.groupproject.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class CartController {
    @Autowired
    private UserService userService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private CartItemService cartItemService;



    // ADD CART ITEM
    @PostMapping("/new_cart_item")
    public String addNewCartItem(@Valid @ModelAttribute("newCartItem") CartItem newCartItem, BindingResult result, HttpSession session) {
        Long userId = (Long) session.getAttribute("loggedInUserId");
        if (userId == null){
            return "redirect:/";
        }
        User user = userService.findUserById(userId);
        newCartItem.setShoppingCart(user.getShoppingCart());
        cartItemService.createNewCartItem(newCartItem);
        return "redirect:/home";
    }

    // DELETE CART ITEM
    @DeleteMapping("/cart_item/{id}/delete")
    public String deleteThisCartItem(@PathVariable("id") Long id, HttpSession session) {
        Long userId = (Long) session.getAttribute("loggedInUserId");
        if (userId == null){
            return "redirect:/";
        }
        cartItemService.deleteThisCartItem(id);
        return "redirect:/home";
    }







    // VIEW - CART ITEMS
    @GetMapping("/shoppingCart")
    public String viewCartItems(HttpSession session, Model model){
        Long userId = (Long) session.getAttribute("loggedInUserId");
        if (userId == null){
            return "redirect:/";
        }
        User user = userService.findUserById(userId);
        model.addAttribute("user", user);

        model.addAttribute("shoppingCart", user.getShoppingCart());     //shoppingCartService.getShoppingCartById(shoppingCartService.getShoppingCartIdByUserId(userId))
        model.addAttribute("cartItems", user.getShoppingCart().getCartItems());     //cartItemService.getAllCartItems()
        return "userViewShoppingCart";
    }

}