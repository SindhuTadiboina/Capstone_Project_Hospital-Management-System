package com.wipro.sindhu.hms.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import com.wipro.sindhu.hms.dto.MedicalRecordDTO;
import com.wipro.sindhu.hms.dto.MedicineDTO;
import com.wipro.sindhu.hms.dto.PatientDTO;
import com.wipro.sindhu.hms.config.EventPublisher;
import com.wipro.sindhu.hms.dto.EventMessage;
import com.wipro.sindhu.hms.services.PatientService;


@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private PatientService patientService;
    private EventPublisher eventPublisher;

    public PatientController(PatientService patientService, EventPublisher eventPublisher) {
        this.patientService = patientService;
        this.eventPublisher = eventPublisher;
    }

    // Create patient (Only ADMIN)
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientDTO patientDTO) {
        PatientDTO savedPatient = patientService.createPatient(patientDTO);

        // Publish Kafka Event
        EventMessage event = new EventMessage(
                "Patient",
                "CREATED",
                "Patient created with ID " + savedPatient.getId()
        );
        eventPublisher.publishPatientEvent(event);

        return ResponseEntity.ok(savedPatient);
    }

    // Get patient by ID
    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatient(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    // Get all patients
    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    // Delete patient
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);

        // Publish Kafka Event
        EventMessage event = new EventMessage(
                "Patient",
                "DELETED",
                "Patient deleted with ID " + id
        );
        eventPublisher.publishPatientEvent(event);

        return ResponseEntity.noContent().build();
    }

    // Add Medical Record to Patient
    @PostMapping("/{patientId}/medical-records")
    public ResponseEntity<MedicalRecordDTO> addMedicalRecord(
            @PathVariable Long patientId,
            @RequestBody MedicalRecordDTO medicalRecordDTO) {

        MedicalRecordDTO savedRecord = patientService.addMedicalRecord(patientId, medicalRecordDTO);

        // Publish Kafka Event
        EventMessage event = new EventMessage(
                "MedicalRecord",
                "ADDED",
                "Medical record added for Patient ID " + patientId
        );
        eventPublisher.publishPatientEvent(event);

        return ResponseEntity.ok(savedRecord);
    }

    // Add Medicine to Medical Record
    @PostMapping("/{patientId}/medical-records/{recordId}/medicines")
    public ResponseEntity<MedicineDTO> addMedicine(
            @PathVariable Long patientId,
            @PathVariable Long recordId,
            @RequestBody MedicineDTO medicineDTO) {

        MedicineDTO savedMedicine = patientService.addMedicine(patientId, recordId, medicineDTO);

        // Publish Kafka Event
        EventMessage event = new EventMessage(
                "Medicine",
                "ADDED",
                "Medicine added for Record ID " + recordId + " of Patient ID " + patientId
        );
        eventPublisher.publishPatientEvent(event);

        return ResponseEntity.ok(savedMedicine);
    }
}
