package com.wipro.sindhu.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wipro.sindhu.hms.dto.AppointmentDTO;
import com.wipro.sindhu.hms.services.AppointmentService;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService service;

    // Patients book appointments
    @PostMapping
    @PreAuthorize("hasAuthority('PATIENT')")
    public AppointmentDTO create(@RequestBody AppointmentDTO dto) {
        return service.createAppointment(dto);
    }

    // Doctors or Admin can update appointments (status, notes, etc.)
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('DOCTOR','ADMIN')")
    public AppointmentDTO update(@PathVariable Long id, @RequestBody AppointmentDTO dto) {
        return service.updateAppointment(id, dto);
    }

    // Patients can view their own, Doctors and Admin can view any
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('PATIENT','DOCTOR','ADMIN')")
    public AppointmentDTO getById(@PathVariable Long id) {
        return service.getAppointmentById(id);
    }

    // Only Admin and Doctors can list all appointments
    @GetMapping
    @PreAuthorize("hasAnyAuthority('DOCTOR','ADMIN')")
    public List<AppointmentDTO> getAll() {
        return service.getAllAppointments();
    }

    // Only Admin can delete appointments
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable Long id) {
        service.deleteAppointment(id);
    }
}
