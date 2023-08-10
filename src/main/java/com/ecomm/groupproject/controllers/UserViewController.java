package com.ecomm.groupproject.controllers;

import com.ecomm.groupproject.models.*;
import com.ecomm.groupproject.models.Category;
import com.ecomm.groupproject.services.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
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
        User user = userService.findUserById(userId);
        model.addAttribute("user", user);

        List<Category> categories = categoryService.getAll();
        model.addAttribute("categories", categories);

        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);

        List<CartItem> cartItems = cartItemService.getAllCartItems();
        model.addAttribute("cartItems", cartItems);     //cartItemService.getCartItemByProductId(products.g)

        List<WishlistItem> wishlistItems = wishlistItemService.getAllWishlistItems();
        model.addAttribute("wishlistItems", wishlistItems);     //cartItemService.getCartItemByProductId(products.g)
        return "userHome";
    }


    // VIEW - BY CATEGORY
    @GetMapping("/{category}")
    public String viewThisCategory(@PathVariable("category") String category, Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("loggedInUserId");
        if (userId == null){
            return "redirect:/";
        }
        User user = userService.findUserById(userId);
        model.addAttribute("user", user);

        List<Category> categories = categoryService.getAll();
        model.addAttribute("categories", categories);

        Category categoryName = categoryService.getByName(category);
        List<Product> products = productService.getByCategoryName(categoryName);
        model.addAttribute("products", products);

        List<CartItem> cartItems = cartItemService.getAllCartItems();
        model.addAttribute("cartItems", cartItems);     //cartItemService.getCartItemByProductId(products.g)

        List<WishlistItem> wishlistItems = wishlistItemService.getAllWishlistItems();
        model.addAttribute("wishlistItems", wishlistItems);     //cartItemService.getCartItemByProductId(products.g)
        return "userCategory";
    }

    // VIEW - PRODUCT
    @GetMapping("/viewProduct/{id}")
    public String viewProduct(@PathVariable("id") Long id,HttpSession session, Model model){
        Long userId = (Long) session.getAttribute("loggedInUserId");
        if (userId == null){
            return "redirect:/";
        }
        model.addAttribute("product", productService.getProductById(id));
        return "userViewProduct";
    }
}
