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

@Controller
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private ShippingDetailsService shippingDetailsService;


    @GetMapping("/")
    public String index(Model model, @ModelAttribute("newUser") User newUser,
                        @ModelAttribute("newLogin") User newLogin, HttpSession session) {
        Long loggedInUserId = (Long) session.getAttribute("loggedInUserId");

        if (loggedInUserId != null) {
            User user = userService.findUserById(loggedInUserId);
            if (user.getRole().getName().equals("ADMIN")) {
                return "redirect:/admin/home";
            } else
                return "redirect:/users/home";
        }
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result,
                           Model model, HttpSession session) {
        userService.register(newUser, result);
        if (result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "index";
        }
        session.setAttribute("loggedInUserId", newUser.getId());

        if (newUser.getRole().getName().equals("ADMIN")) {
            return "redirect:/admin/home";
        } else
            return "redirect:/users/home";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result,
                        Model model, HttpSession session) {
        User user = userService.login(newLogin, result);

        if (result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index";
        }
        session.setAttribute("loggedInUserId", user.getId());


        if (user.getRole().getName().equals("ADMIN")) {
            return "redirect:/admin/home";
        } else
            return "redirect:/users/home";
    }

    @GetMapping("/admin/home")
    public String dashboard(HttpSession session, Model model) {
        Long loggedInUserId = (Long) session.getAttribute("loggedInUserId");
        if (loggedInUserId == null) {
            return "redirect:/";
        }
        User loggedInUser=userService.findUserById(loggedInUserId);
        model.addAttribute("user",loggedInUser);
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("orders", orderService.getAll());
        return "viewOrders";
    }
    @GetMapping("/admin/newCat")
    public String addCategory(@ModelAttribute("newCategory") Category newCategory, HttpSession session, Model model) {
        Long loggedInUserId = (Long) session.getAttribute("loggedInUserId");
        if (loggedInUserId == null) {
            return "redirect:/";
        }
        model.addAttribute("category", categoryService.getAll());
        return "newCategory";
    }

    @PostMapping("/admin/newCategory")
    public String createCategory(@Valid@ModelAttribute("newCategory") Category newCategory, BindingResult result, Model model) {
        Category category=categoryService.create(newCategory,result);
        if (result.hasErrors()) {
            return "newCategory";
        }
        return "redirect:/admin/newCat";
    }

    @GetMapping("/admin/newP")
    public String addCategory(@ModelAttribute("newProduct") Product newProduct, HttpSession session, Model model) {
        Long loggedInUserId = (Long) session.getAttribute("loggedInUserId");
        if (loggedInUserId == null) {
            return "redirect:/";
        }
        model.addAttribute("category", categoryService.getAll());
        return "newProduct";
    }
    @PostMapping("/admin/newProduct")
    public String createProduct(@Valid@ModelAttribute("newProduct") Product newProduct, BindingResult result, @RequestParam Long categoryId, Model model) {
        if (result.hasErrors()) {
            return "newProduct";
        }
        Category category = categoryService.findById(categoryId);
        newProduct.setCategory(category);
        productService.create(newProduct);
        return "redirect:/admin/home";
    }

    @GetMapping("/admin/category/{id}")
    public String getByCategory(@PathVariable("id")Long id, Model model, HttpSession session){
        Long loggedInUserId = (Long) session.getAttribute("loggedInUserId");
        if (loggedInUserId == null) {
            return "redirect:/";
        }
        Category category=categoryService.findById(id);
        model.addAttribute("products", productService.getByCategory(category));
        model.addAttribute("category", categoryService.getAll());
        return "viewByCategory";
    }

    @DeleteMapping("/admin/{id}/delete")
    public String delete(@PathVariable("id")Long id, HttpSession session){
        productService.deleteProduct(id);
        return "redirect:/admin/home";
    }


    @DeleteMapping("/admin/{id}/deleteCat")
    public String deleteCat(@PathVariable("id")Long id, HttpSession session){
        categoryService.deleteCategory(id);
        return "redirect:/admin/newCat";
    }

    @GetMapping("/admin/{id}/edit")
    public String editProduct(@PathVariable("id") Long id, HttpSession session, Model model) {
        if (session.getAttribute("loggedInUserId") == null) {
            return "redirect:/";
        }
        Product editProduct = productService.find(id);
        model.addAttribute("category", categoryService.getAll());
        model.addAttribute("editProduct", editProduct);
        return "editP";
    }

    @PostMapping("/admin/edit/{id}")
    public String edit(@Valid @ModelAttribute("editProduct") Product editProduct, BindingResult result,
                       @PathVariable("id") Long id) {
        if (result.hasErrors()) {
            return "editP";
        } else {
            Product existingProduct=productService.find(id);
            Category category = categoryService.findById(editProduct.getCategory().getId());
            existingProduct.setProductName(editProduct.getProductName());
            existingProduct.setBrand(editProduct.getBrand());
            existingProduct.setColor(editProduct.getColor());
            existingProduct.setDescription(editProduct.getDescription());
            existingProduct.setPrice(editProduct.getPrice());
            existingProduct.setQuantity(editProduct.getQuantity());
            existingProduct.setImage(editProduct.getImage());
            existingProduct.setCategory(category);
            productService.update(existingProduct);
            return "redirect:/admin/home";
        }
    }

    @GetMapping("/admin/orderDetails/{id}")
    public String orderDetails(@PathVariable("id") Long id, Model model,HttpSession session){
        if (session.getAttribute("loggedInUserId") == null) {
            return "redirect:/";
        }
        Order order=orderService.findById(id);
        model.addAttribute("order", order);
        model.addAttribute("categories", categoryService.getAll());
        return "viewOrderDetails";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/viewCart")
    public String viewCart(Model model, HttpSession session) {
        Long loggedInUserId = (Long) session.getAttribute("loggedInUserId");

        if (loggedInUserId != null) {
            ShoppingCart cart = shoppingCartService.getShoppingCartById(loggedInUserId);

            if (cart == null) {
                // Handle the case where the cart is null
                model.addAttribute("cartItems", new ArrayList<CartItem>()); // Empty list
                model.addAttribute("totalPrice", 0.0); // Total price is 0
            } else {
                model.addAttribute("cartItems", cart.getCartItems());
                double totalPrice = cart.getCartItems().stream()
                        .mapToDouble(cartItem -> cartItem.getProduct().getPrice())
                        .sum();
                model.addAttribute("totalPrice", totalPrice);

                if (!cart.getCartItems().isEmpty()) {
                    return "redirect:/shippingDetails";
                }
            }
        } else {
            return "redirect:/";
        }

        return "shoppingCart";
    }


    @PostMapping("/shippingDetails")
    public String processShippingDetailsForm(@Valid @ModelAttribute("shippingDetails") ShippingDetails shippingDetails,
                                             BindingResult result, HttpSession session) {
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



