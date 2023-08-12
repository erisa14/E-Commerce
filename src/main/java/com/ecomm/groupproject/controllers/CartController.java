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
    private ProductService productService;
    @Autowired
    private WishlistItemService wishlistItemService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private ShippingDetailsService shippingDetailsService;
    @Autowired
    private CartItemService cartItemService;


    // VIEW - CART
    @GetMapping("/viewCart")
    public String viewCart(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("loggedInUserId");
        if (userId == null){
            return "redirect:/";
        }
        model.addAttribute("user", userService.findUserById(userId));
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("products", productService.getAllProducts());
        List<CartItem> cartItems = cartItemService.getAllCartItems();
        int numberOfCartItems = cartItems.size();
        List<WishlistItem> wishlistItems = wishlistItemService.getAllWishlistItems();
        int numberOfWishlistItems = wishlistItems.size();
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("numberOfCartItems", numberOfCartItems);
        model.addAttribute("wishlistItems", wishlistItems);
        model.addAttribute("numberOfWishlistItems", numberOfWishlistItems);

        double totalPrice = cartItems.stream().mapToDouble(cartItem -> cartItem.getProduct().getPrice()).sum();
        model.addAttribute("totalPrice", totalPrice);
        return "shoppingCart";
    }


    // ADD CART ITEM
    @PostMapping("/new_cart_item")
    public String addNewCartItem(@Valid @ModelAttribute("newCartItem") CartItem newCartItem, BindingResult result, @RequestParam("productId") Long productId, HttpSession session) {
        Long userId = (Long) session.getAttribute("loggedInUserId");
        if (userId == null){
            return "redirect:/";
        }
        User user = userService.findUserById(userId);
        ShoppingCart shoppingCart = user.getShoppingCart();

        Product product = new Product();        // Create a new Product instance using the retrieved values
        product.setId(productId);
        newCartItem.setProduct(product);        // Set the Product instance to the newCartItem
        newCartItem.setShoppingCart(shoppingCart);        // Set the ShoppingCart and other necessary fields
        cartItemService.addNewCartItem(newCartItem);        // Add the newCartItem to the cart
        return "redirect:/users/home";
    }


    // DELETE CART ITEM
    @DeleteMapping("/cart_item/{id}/delete")
    public String deleteCartItem(@PathVariable("id") Long id, HttpSession session) {
        Long userId = (Long) session.getAttribute("loggedInUserId");
        if (userId == null){
            return "redirect:/";
        }
        cartItemService.deleteThisCartItem(id);
        return "redirect:/viewCart";
    }





    // ADD SHIPPING DETAILS
    @PostMapping("/shippingDetails")
    public String processShippingDetailsForm(@Valid @ModelAttribute("shippingDetails") ShippingDetails shippingDetails, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            return "shippingDetails";
        }
        Long loggedInUserId = (Long) session.getAttribute("loggedInUserId");
        User user = userService.findUserById(loggedInUserId);
        shippingDetails.setUser(user);
        shippingDetailsService.saveShippingDetails(shippingDetails);
        return "redirect:/charge";
    }

}