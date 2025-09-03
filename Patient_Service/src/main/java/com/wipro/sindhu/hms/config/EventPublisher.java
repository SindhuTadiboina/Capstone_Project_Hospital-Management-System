package com.wipro.sindhu.hms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.sindhu.hms.dto.EventMessage;

import org.springframework.kafka.core.KafkaTemplate;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventPublisher {
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	@Autowired
    private ObjectMapper objectMapper = new ObjectMapper();

    public void publishPatientEvent(EventMessage eventMessage) {
        try {
            String message = objectMapper.writeValueAsString(eventMessage);
            kafkaTemplate.send("patient-topic", message);
            System.out.println("Sent Patient Event: " + message);
        } catch (Exception e) {
            System.err.println("Error sending Kafka message: " + e.getMessage());
        }
    }

}
