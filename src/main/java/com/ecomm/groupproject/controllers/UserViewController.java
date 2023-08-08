package com.ecomm.groupproject.controllers;

import com.ecomm.groupproject.models.Category;
import com.ecomm.groupproject.models.Product;
import com.ecomm.groupproject.models.User;
import com.ecomm.groupproject.services.CategoryService;
import com.ecomm.groupproject.services.ProductService;
import com.ecomm.groupproject.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/users")
public class UserViewController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;


    // VIEW - DASHBOARD
    @GetMapping("/home")
    public String dashboardUser(HttpSession session, Model model){
        Long userId = (Long) session.getAttribute("loggedInUserId");
        if (userId == null){
            return "redirect:/";
        }
        User user = userService.findUserById(userId);
        model.addAttribute("user", user);

        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("products", productService.getAllProducts());
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

        Category categoryName = categoryService.getByName(category);
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("products", productService.getByCategoryName(categoryName));
        return "userCategory";
    }

    @GetMapping("/view/{productId}")
    public String ViewProduct(@PathVariable("productId") Long productId, HttpSession session, Model model){
        Long userId = (Long) session.getAttribute("loggedInUserId");
        if (userId == null){
            return "redirect:/";
        }

        Product product = productService.find(productId);
        model.addAttribute("product", product);

        return"viewProductDetails";

    }
}
