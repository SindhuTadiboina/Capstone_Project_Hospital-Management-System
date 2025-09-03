package com.wipro.sindhu.hms.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.wipro.sindhu.hms.dto.MedicalRecordDTO;
import com.wipro.sindhu.hms.dto.MedicineDTO;
import com.wipro.sindhu.hms.dto.PatientDTO;
import com.wipro.sindhu.hms.entities.MedicalRecord;
import com.wipro.sindhu.hms.entities.Medicine;
import com.wipro.sindhu.hms.entities.Patient;
import com.wipro.sindhu.hms.enums.Gender;
import com.wipro.sindhu.hms.exception.ResourceNotFoundException;
import com.wipro.sindhu.hms.repository.MedicalRecordRepository;
import com.wipro.sindhu.hms.repository.MedicienRepository;
import com.wipro.sindhu.hms.repository.PatientRepository;


@Service
public class PatientServiceImpl implements PatientService {
	
	private PatientRepository patientRepository;
    private MedicalRecordRepository medicalRecordRepository;
    private MedicienRepository medicienRepository;

    public PatientServiceImpl(PatientRepository patientRepository,
                              MedicalRecordRepository medicalRecordRepository,
                              MedicienRepository medicienRepository) {
        this.patientRepository = patientRepository;
        this.medicalRecordRepository = medicalRecordRepository;
        this.medicienRepository = medicienRepository;
    }

    // Patient methods
    @Override
    public PatientDTO createPatient(PatientDTO patientDTO) {
        Patient patient = new Patient();
        patient.setName(patientDTO.getName());
        patient.setAddress(patientDTO.getAddress());
        patient.setPhoneNumber(patientDTO.getPhoneNumber());

        // gender mapping
        if (patientDTO.getGender() != null) {
            try {
                patient.setGender(Gender.valueOf(patientDTO.getGender().toUpperCase()));
            } catch (IllegalArgumentException ex) {
                throw new RuntimeException("Invalid gender value. Allowed values: MALE, FEMALE, OTHER");
            }
        }

        patient.setDob(patientDTO.getDob());
        patient.setWeight(patientDTO.getWeight());
        patient.setHeight(patientDTO.getHeight());

        patientRepository.save(patient);

        // Return DTO with generated ID
        patientDTO.setId(patient.getId());
        return patientDTO;
    }

    @Override
    public PatientDTO getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        PatientDTO dto = new PatientDTO();
        dto.setId(patient.getId());
        dto.setName(patient.getName());
        dto.setAddress(patient.getAddress());
        dto.setPhoneNumber(patient.getPhoneNumber());
        dto.setDob(patient.getDob());
        dto.setHeight(patient.getHeight());
        dto.setWeight(patient.getWeight());
        return dto;
    }

    @Override
    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(p -> {
                    PatientDTO dto = new PatientDTO();
                    dto.setId(p.getId());
                    dto.setName(p.getName());
                    dto.setAddress(p.getAddress());
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Patient not found");
        }
        patientRepository.deleteById(id);
    }

    // MedicalRecord methods
    @Override
    public MedicalRecordDTO addMedicalRecord(Long patientId, MedicalRecordDTO recordDTO) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        MedicalRecord record = new MedicalRecord();
        record.setWhoDoctor(recordDTO.getWhoDoctor());
        record.setTreatedAt(recordDTO.getTreatedAt());
        record.setRevisitingTime(recordDTO.getRevisitingTime());
        record.setPatient(patient);

        medicalRecordRepository.save(record);

        recordDTO.setId(record.getId());
        return recordDTO;
    }

    // Medicine methods
    @Override
    public MedicineDTO addMedicine(Long patientId, Long recordId, MedicineDTO medicienDTO) {
        // Validate patient exists
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        // Validate record belongs to patient
        MedicalRecord record = medicalRecordRepository.findById(recordId)
                .orElseThrow(() -> new ResourceNotFoundException("Medical record not found"));

        if (!record.getPatient().getId().equals(patient.getId())) {
            throw new ResourceNotFoundException("Medical record does not belong to patient");
        }

        Medicine medicien = new Medicine();
        medicien.setMedicienName(medicienDTO.getMedicienName());
        medicien.setQuantity(medicienDTO.getQuantity());
        medicien.setDosageTime(medicienDTO.getDosageTime());
        medicien.setMedicalRecord(record);

        medicienRepository.save(medicien);

        medicienDTO.setId(medicien.getId());
        return medicienDTO;
    }

}
