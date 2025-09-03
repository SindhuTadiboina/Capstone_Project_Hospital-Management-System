package com.wipro.sindhu.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class EmailConsumer {

    private EmailService emailService;

    // Logger
    private static final Logger logger = Logger.getLogger(EmailConsumer.class.getName());

    // Constructor injection
    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "payment-topic", groupId = "email-group")
    public void consume(Payment payment) {
        logger.warning("Received Payment: " + payment.getUserEmail() + ", Amount: " + payment.getAmount());
        emailService.sendPaymentEmail(payment);
    }
}
