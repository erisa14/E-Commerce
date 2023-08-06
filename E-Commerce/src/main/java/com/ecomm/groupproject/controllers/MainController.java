package com.ecomm.groupproject.controllers;

import com.ecomm.groupproject.models.LoginUser;
import com.ecomm.groupproject.models.User;
import com.ecomm.groupproject.services.OrderService;
import com.ecomm.groupproject.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public String index(Model model, @ModelAttribute("newUser") User newUser,
                        @ModelAttribute("newLogin")User newLogin, HttpSession session){
        Long loggedInUserId=(Long) session.getAttribute("loggedInUserId");

        if (loggedInUserId!=null){
            User user=userService.findUserById(loggedInUserId);
            if (user.getRole().getName().equals("ADMIN")) {
                return "redirect:/admin/home";
            }
            else
                return "redirect:/users/home";
        }
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser")User newUser, BindingResult result,
                           Model model, HttpSession session){
        userService.register(newUser, result);
        if (result.hasErrors()){
            model.addAttribute("newLogin", new LoginUser());
            return "index";
        }
        session.setAttribute("loggedInUserId", newUser.getId());

        if (newUser.getRole().getName().equals("ADMIN")) {
            return "redirect:/admin/home";
        }
        else
            return "redirect:/users/home";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin")LoginUser newLogin, BindingResult result,
                        Model model, HttpSession session){
        User user=userService.login(newLogin, result);

        if (result.hasErrors()){
            model.addAttribute("newUser", new User());
            return "index";
        }
        session.setAttribute("loggedInUserId", user.getId());


        if (user.getRole().getName().equals("ADMIN")) {
            return "redirect:/admin/home";
        }
        else
            return "redirect:/index";
    }
    @GetMapping("/admin/home")
    public String dashboard(HttpSession session, Model model){
        Long loggedInUserId=(Long) session.getAttribute("loggedInUserId");
        if (loggedInUserId==null){
            return "redirect:/";
        }
        User loggedInUser=userService.findUserById(loggedInUserId);
        model.addAttribute("user",loggedInUser);
        model.addAttribute("orders", orderService.getAll());
        return "viewOrders";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
