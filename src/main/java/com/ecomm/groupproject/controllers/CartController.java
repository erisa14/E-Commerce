package com.ecomm.groupproject.controllers;

import com.ecomm.groupproject.models.*;
import com.ecomm.groupproject.services.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class CartController {
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private CartItemService cartItemService;


    // ADD CART ITEM
    @PostMapping("/new_cart_item")
    public String addNewCartItem(@Valid @ModelAttribute("newCartItem") CartItem newCartItem, BindingResult result, @RequestParam("productId") Long productId, HttpSession session) {
        Long userId = (Long) session.getAttribute("loggedInUserId");
        if (userId == null){
            return "redirect:/";
        }
        User user = userService.findUserById(userId);
        ShoppingCart shoppingCart = user.getShoppingCart();

        Product product = new Product();
        product.setId(productId);        // Create a new Product instance using the retrieved values
        newCartItem.setProduct(product);        // Set the Product instance to the newCartItem
        newCartItem.setShoppingCart(shoppingCart);        // Set the ShoppingCart and other necessary fields
        cartItemService.addNewCartItem(newCartItem);        // Add the newCartItem to the cart
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
        return "redirect:/viewCart";
    }







    // VIEW - CART
    @GetMapping("/viewCart")
    public String viewCart(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("loggedInUserId");
        if (userId != null) {
            User user = userService.findUserById(userId);
            model.addAttribute("user", user);
            List<Category> categories = categoryService.getAll();
            model.addAttribute("categories", categories);


            ShoppingCart cart = shoppingCartService.getShoppingCartByUserId(userId);
            Long cartId = cart.getId(); // Get the cartId from the user's shopping cart
            List<CartItem> cartItems =  cartItemService.getCartItemsByUserId(cartId);

            if (cartItems.isEmpty()) {
                model.addAttribute("cartItems", new ArrayList<CartItem>());
                model.addAttribute("totalPrice", 0.0);
            } else {
                model.addAttribute("cartItems", cartItems);
                double totalPrice = cartItems.stream().mapToDouble(cartItem -> cartItem.getProduct().getPrice()).sum();
                model.addAttribute("totalPrice", totalPrice);
            }
            return "shoppingCart";
        }
        else {
            return "redirect:/";
        }
    }


    /*
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
     */

}