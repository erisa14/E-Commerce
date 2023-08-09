package com.ecomm.groupproject.controllers;

import com.ecomm.groupproject.services.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import lombok.SneakyThrows;
import org.aspectj.weaver.ast.Call;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.ecomm.groupproject.models.ChargeRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.AuthenticationException;

@Controller
public class CheckoutController {

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    // Other properties and constructor


    @RequestMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("amount", 50 * 100); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.EUR);
        return "checkout";
    }
}



