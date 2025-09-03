package com.wipro.sindhu.kafka;

import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private PaymentProducer paymentProducer;

    // Logger
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    // Constructor injection
    public PaymentController(PaymentProducer paymentProducer) {
        this.paymentProducer = paymentProducer;
    }

    @PostMapping
    public Payment makePayment(@RequestBody Payment payment) {
        if (payment.getAmount() <= 0) {
            payment.setStatus("FAIL");
            payment.setMessage("Payment failed! Invalid amount: " + payment.getAmount());
        } else {
            payment.setStatus("SUCCESS");
            payment.setMessage("Your payment of " + payment.getAmount() + " is successful!");
        }

        logger.warn("Payment object: {}", payment);
        paymentProducer.sendPayment(payment);
        return payment;
    }
}
