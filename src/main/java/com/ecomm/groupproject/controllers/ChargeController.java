package com.ecomm.groupproject.controllers;

import com.ecomm.groupproject.models.*;
import com.ecomm.groupproject.repositories.OrderItemRepository;
import com.ecomm.groupproject.repositories.OrderRepository;
import com.ecomm.groupproject.services.OrderService;
import com.ecomm.groupproject.services.ShoppingCartService;
import com.ecomm.groupproject.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.ui.Model;
import com.ecomm.groupproject.services.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.naming.AuthenticationException;
import java.util.List;

@Log
@Controller
public class ChargeController {

    @Autowired
    StripeService paymentsService;
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @PostMapping("/charge")
    public String charge(
            @RequestParam(name = "amount") double amount,
            @RequestParam(name = "stripeToken") String stripeToken,
            Model model, HttpSession session) {
        Long loggedInUserId = (Long) session.getAttribute("loggedInUserId");
        User user = userService.findUserById(loggedInUserId);


        ChargeRequest chargeRequest = new ChargeRequest();
        chargeRequest.setDescription("Example charge");
        chargeRequest.setCurrency(ChargeRequest.Currency.EUR);
        chargeRequest.setAmount((int) (amount * 100)); // Convert amount to cents
        chargeRequest.setStripeToken(stripeToken);

        try {
            Charge charge = paymentsService.charge(chargeRequest);
            model.addAttribute("id", charge.getId());
            model.addAttribute("status", charge.getStatus());
            model.addAttribute("chargeId", charge.getId());
            model.addAttribute("balance_transaction", charge.getBalanceTransaction());
            return "result.jsp";
        } catch (StripeException e) {
            model.addAttribute("error", e.getMessage());
            return "result.jsp";
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "result.jsp";
    }
}
