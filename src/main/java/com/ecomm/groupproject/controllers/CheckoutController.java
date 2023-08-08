package com.ecomm.groupproject.controllers;

import com.ecomm.groupproject.services.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import lombok.SneakyThrows;
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

    private final StripeService stripeService;

    @Autowired
    public CheckoutController(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @RequestMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("amount", 50 * 100); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.EUR);
        return "checkout";
    }

    @SneakyThrows
    @PostMapping("/checkout")
    public String handleCheckout(@RequestParam("totalPrice") double totalPrice, @RequestParam("stripeToken") String stripeToken, Model model) throws StripeException {
        // Process the payment logic here using the totalPrice and stripeToken

        ChargeRequest chargeRequest = new ChargeRequest();
        chargeRequest.setAmount((int) (totalPrice * 100)); // Convert totalPrice to cents
        chargeRequest.setCurrency(ChargeRequest.Currency.EUR);
        chargeRequest.setDescription("Payment for Order");
        chargeRequest.setStripeToken(stripeToken); // Set the stripeToken obtained from the frontend

        // Call the StripeService to process the payment
        Charge charge = stripeService.charge(chargeRequest);

        // Handle the successful payment here
        model.addAttribute("charge", charge);

        // Return the success page after successful payment
        return "paymentSuccess"; // You can create a "paymentSuccess.jsp" to show the payment success message
    }
}


