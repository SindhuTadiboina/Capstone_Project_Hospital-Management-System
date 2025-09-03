package com.wipro.sindhu.hms.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wipro.sindhu.hms.dto.DoctorDTO;
import com.wipro.sindhu.hms.services.DoctorService;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
	
	private final DoctorService service;

    public DoctorController(DoctorService service) {
        this.service = service;
    }

    // Only ADMIN can create a doctor
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<DoctorDTO> create(@RequestBody DoctorDTO dto) {
        return ResponseEntity.ok(service.createDoctor(dto));
    }

    // ADMIN and DOCTOR can get doctor by ID
    @PreAuthorize("hasAnyAuthority('ADMIN','DOCTOR')")
    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getDoctorById(id));
    }

    // Only ADMIN can get all doctors
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<DoctorDTO>> getAll() {
        return ResponseEntity.ok(service.getAllDoctors());
    }
}
