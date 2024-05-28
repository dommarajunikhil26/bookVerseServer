package com.nikhil.bookVerse.controller;

import com.nikhil.bookVerse.repository.PaymentRepository;
import com.nikhil.bookVerse.requestmodels.PaymentInfoRequest;
import com.nikhil.bookVerse.service.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/api/secure/secure")
public class PaymentController {
    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping("/payment-intent")
    public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentInfoRequest paymentInfoRequest)
        throws StripeException {
        PaymentIntent paymentIntent = paymentService.createPaymentIntent(paymentInfoRequest);
        String paymentStr = paymentIntent.toJson();

        return new ResponseEntity<>(paymentStr, HttpStatus.OK);
    }

    @PutMapping("/payment-complete")
    public ResponseEntity<String> stripePaymentComplete(HttpServletRequest request) throws Exception{
        String userEmail = (String) request.getAttribute("email");
        if (userEmail == null) {
            throw new RuntimeException("User email not found in request attributes.");
        }
        return paymentService.stripePayment(userEmail);
    }
}
