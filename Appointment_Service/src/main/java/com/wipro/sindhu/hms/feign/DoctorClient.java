package com.wipro.sindhu.hms.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.wipro.sindhu.hms.dto.DoctorDTO;

@FeignClient(name = "doctor-service", url = "http://localhost:8082")
public interface DoctorClient {

    @GetMapping("/api/doctors/{id}")  
    DoctorDTO getDoctorById(@PathVariable("id") Long id);
}



