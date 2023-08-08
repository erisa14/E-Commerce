package com.ecomm.groupproject.controllers;

import com.ecomm.groupproject.models.Category;
import com.ecomm.groupproject.models.User;
import com.ecomm.groupproject.models.Category;
import com.ecomm.groupproject.models.Product;
import com.ecomm.groupproject.services.CategoryService;
import com.ecomm.groupproject.services.ProductService;
import com.ecomm.groupproject.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
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


    @PostMapping("/{productId}/wishlist")
    public ResponseEntity<String> addToWishlist(@PathVariable Long productId) {
        productService.addToWishlist(productId);
        return ResponseEntity.ok("Product added to wishlist.");
    }

    @DeleteMapping("/{productId}/wishlist")
    public ResponseEntity<String> removeFromWishlist(@PathVariable Long productId) {
        productService.removeFromWishlist(productId);
        return ResponseEntity.ok("Product removed from wishlist.");
    }



    /*/SEARCH
    @GetMapping("/search")
    public ModelAndView searchProducts(@RequestParam(required = false) String searchTerm) {
        ModelAndView modelAndView = new ModelAndView();

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            modelAndView.addObject("message", "Please enter a search term.");
        } else {
            List<Product> products = productService.searchProducts(searchTerm);
            modelAndView.addObject("products", products);
        }

        // Assuming "home.jsp" is the view you want to return
        modelAndView.setViewName("userHome");
        return modelAndView;
    }*/


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


    /* VIEW - PRODUCT
    @GetMapping("/{category}/{id}")
    public String viewThisProduct(@PathVariable("id") Long id, @PathVariable("category") String category, Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("loggedInUserId");
        if (userId == null){
            return "redirect:/";
        }
        User user = userService.findUserById(userId);
        model.addAttribute("user", user);
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("product", productService.getProductById(id));
        return "viewProduct";
    }

     */
}
