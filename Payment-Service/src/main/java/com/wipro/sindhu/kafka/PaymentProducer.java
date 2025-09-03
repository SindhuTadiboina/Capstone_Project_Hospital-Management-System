package com.wipro.sindhu.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentProducer {

    @Autowired
    private KafkaTemplate<String, Payment> kafkaTemplate;

    public void sendPayment(Payment payment) {
        kafkaTemplate.send("payment-topic", payment);
    }
}

