package com.ecomm.groupproject.controllers;

import com.ecomm.groupproject.services.StripeService;
import org.springframework.ui.Model;
import com.ecomm.groupproject.models.ChargeRequest;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;


import javax.naming.AuthenticationException;

@Log
@Controller
public class ChargeController {

    @Autowired
    StripeService paymentsService;

    @PostMapping("/charge")
    public String charge(ChargeRequest chargeRequest, Model model) throws StripeException {
        chargeRequest.setDescription("Example charge");
        chargeRequest.setCurrency(ChargeRequest.Currency.EUR);
        Charge charge = null;
        try {
            charge = paymentsService.charge(chargeRequest);
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
        return "result";
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "result";
    }
}
