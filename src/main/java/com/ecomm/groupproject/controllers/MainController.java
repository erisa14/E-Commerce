package com.ecomm.groupproject.controllers;

import com.ecomm.groupproject.models.LoginUser;
import com.ecomm.groupproject.models.User;
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

    @GetMapping("/")
    public String index(Model model, @ModelAttribute("newUser") User newUser,
                        @ModelAttribute("newLogin")User newLogin, HttpSession session){
        Long loggedInUserId=(Long) session.getAttribute("loggedInUserId");

        if (loggedInUserId!=null){
            return "redirect: /dashboard";
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
        return "redirect:/dashboard";
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
        return "redirect:/dashboard";
    }
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model){
        Long loggedInUserId=(Long) session.getAttribute("loggedInUserId");
        if (loggedInUserId==null){
            return "redirect:/";
        }
        User loggedInUser=userService.findUserById(loggedInUserId);
        model.addAttribute("user",loggedInUser);
        return "dashboard";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
