package com.wipro.sindhu.hms.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.wipro.sindhu.hms.dto.PatientDTO;

@FeignClient(name = "patient-service", url = "http://localhost:8081")
public interface PatientClient {
    @GetMapping("/api/patients/{id}")
    PatientDTO getPatientById(@PathVariable("id") Long id);
}