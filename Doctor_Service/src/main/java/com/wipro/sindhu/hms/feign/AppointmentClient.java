package com.wipro.sindhu.hms.feign;


import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.wipro.sindhu.hms.dto.AppointmentDto;


@FeignClient(name = "appointment-service", url = "http://localhost:8083")
public interface AppointmentClient {

    @GetMapping("/api/appointments/doctor/{doctorId}")
    List<AppointmentDto> getAppointmentsByDoctor(@PathVariable("doctorId") Long doctorId);

}
