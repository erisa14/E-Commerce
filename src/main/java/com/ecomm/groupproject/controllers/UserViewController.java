package com.ecomm.groupproject.controllers;

import com.ecomm.groupproject.models.*;
import com.ecomm.groupproject.services.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/users")
public class UserViewController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private WishlistItemService wishlistItemService;


    // VIEW - DASHBOARD
    @GetMapping("/home")
    public String dashboardUser(HttpSession session, Model model){
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
        return "userHome";
    }


    // VIEW - BY CATEGORY
    @GetMapping("/{category}")
    public String viewThisCategory(@PathVariable("category") String category, Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("loggedInUserId");
        if (userId == null){
            return "redirect:/";
        }
        model.addAttribute("user", userService.findUserById(userId));
        model.addAttribute("categories", categoryService.getAll());
        Category categoryName = categoryService.getByName(category);
        model.addAttribute("categoryName", categoryService.getByName(category));
        model.addAttribute("products", productService.getByCategoryName(categoryName));
        List<CartItem> cartItems = cartItemService.getAllCartItems();
        int numberOfCartItems = cartItems.size();
        List<WishlistItem> wishlistItems = wishlistItemService.getAllWishlistItems();
        int numberOfWishlistItems = wishlistItems.size();
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("numberOfCartItems", numberOfCartItems);
        model.addAttribute("wishlistItems", wishlistItems);
        model.addAttribute("numberOfWishlistItems", numberOfWishlistItems);
        return "userCategory";
    }


    // VIEW - PRODUCT
    @GetMapping("/view/{productId}")
    public String ViewProduct(@PathVariable("productId") Long productId, HttpSession session, Model model){
        Long userId = (Long) session.getAttribute("loggedInUserId");
        if (userId == null){
            return "redirect:/";
        }
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("product", productService.find(productId));
        List<CartItem> cartItems = cartItemService.getAllCartItems();
        int numberOfCartItems = cartItems.size();
        List<WishlistItem> wishlistItems = wishlistItemService.getAllWishlistItems();
        int numberOfWishlistItems = wishlistItems.size();
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("numberOfCartItems", numberOfCartItems);
        model.addAttribute("wishlistItems", wishlistItems);
        model.addAttribute("numberOfWishlistItems", numberOfWishlistItems);
        return "viewProductDetails";
    }

}