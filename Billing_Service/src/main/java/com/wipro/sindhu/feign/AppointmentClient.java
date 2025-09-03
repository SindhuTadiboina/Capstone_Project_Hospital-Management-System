package com.wipro.sindhu.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.wipro.sindhu.dto.AppointmentDto;

@FeignClient(name = "appointment-service", url = "http://localhost:8085")
public interface AppointmentClient {
    @GetMapping("/api/appointments/{id}")
    AppointmentDto getAppointmentById(@PathVariable("id") Long id);
}

