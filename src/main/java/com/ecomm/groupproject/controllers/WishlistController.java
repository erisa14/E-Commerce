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
public class WishlistController {
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private WishlistService wishlistService;
    @Autowired
    private WishlistItemService wishlistItemService;
    @Autowired
    private CartItemService cartItemService;


    // VIEW - WISHLIST
    @GetMapping("/viewWishlist")
    public String viewWishlist(HttpSession session, Model model){
        Long userId = (Long) session.getAttribute("loggedInUserId");
        if (userId == null){
            return "redirect:/";
        }
        User user = userService.findUserById(userId);
        model.addAttribute("user", user);
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categories", categories);
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);

        Wishlist wishlist = wishlistService.getWishlistByUserId(userId);
        Long wishlistId = wishlist.getId(); // Get the wishlistId from the user's shopping cart
        List<WishlistItem> wishlistItems =  wishlistItemService.getWishlistItemsByUserId(wishlistId);

        if (wishlistItems.isEmpty()) {
            model.addAttribute("wishlistItems", new ArrayList<WishlistItem>());
        }
        else {
            model.addAttribute("wishlistItems", wishlistItems);
        }
        return "wishlist.jsp";
    }


    // ADD WISHLIST ITEM
    @PostMapping("/new_wishlist_item")
    public String addNewWishlistItem(@Valid @ModelAttribute("newWishlistItem") WishlistItem newWishlistItem, BindingResult result, @RequestParam("productId") Long productId, HttpSession session) {
        Long userId = (Long) session.getAttribute("loggedInUserId");
        if (userId == null){
            return "redirect:/";
        }
        User user = userService.findUserById(userId);
        Wishlist wishlist = user.getWishlist();
        Product product = new Product();

        product.setId(productId);        // Create a new Product instance using the retrieved values
        newWishlistItem.setProduct(product);        // Set the Product instance to the newCartItem
        newWishlistItem.setWishlist(wishlist);        // Set the ShoppingCart and other necessary fields
        wishlistItemService.addNewWishlistItem(newWishlistItem);        // Add the newCartItem to the cart

        return "redirect:/users/home";
    }


    // DELETE CART ITEM
    @DeleteMapping("/wishlist_item/{id}/delete")
    public String deleteThisWishlistItem(@PathVariable("id") Long id, HttpSession session) {
        Long userId = (Long) session.getAttribute("loggedInUserId");
        if (userId == null){
            return "redirect:/";
        }
        wishlistItemService.deleteThisWishlistItem(id);
        return "redirect:/viewWishlist";
    }



    // ADD ITEMS OF WISHLIST TO CART AND DELETE THEM FROM WISHLIST
    @PostMapping("/add_wishlist_item_to_cart")
    public String addWishlistItemToCart(@RequestParam("productIds") List<Long> productIds, HttpSession session) {
        Long userId = (Long) session.getAttribute("loggedInUserId");
        if (userId == null) {
            return "redirect:/";
        }
        User user = userService.findUserById(userId);
        ShoppingCart shoppingCart = user.getShoppingCart();
        for (Long productId : productIds) {
            Product product = productService.getProductById(productId);
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setShoppingCart(shoppingCart);
            cartItemService.addNewCartItem(cartItem);
            // Remove the product from the wishlist
            WishlistItem wishlistItem = wishlistItemService.getWishlistItemByProductId(productId);
            wishlistItemService.deleteThisWishlistItem(wishlistItem.getId());
        }
        return "redirect:/viewWishlist";
    }

}