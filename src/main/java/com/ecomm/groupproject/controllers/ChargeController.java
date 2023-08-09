package com.ecomm.groupproject.controllers;

import org.springframework.ui.Model;
import com.ecomm.groupproject.models.ChargeRequest;
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

@Log
@Controller
public class ChargeController {

    @Autowired
    StripeService paymentsService;

    @PostMapping("/charge")
    public String charge(
            @RequestParam(name = "amount") double amount,
            @RequestParam(name = "stripeToken") String stripeToken,
            Model model) {
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
            return "result";
        } catch (StripeException e) {
            model.addAttribute("error", e.getMessage());
            return "result";
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "result";
    }
}

