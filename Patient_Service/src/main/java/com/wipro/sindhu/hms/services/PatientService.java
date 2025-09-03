package com.wipro.sindhu.hms.services;

import java.util.List;

import com.wipro.sindhu.hms.dto.MedicalRecordDTO;
import com.wipro.sindhu.hms.dto.MedicineDTO;
import com.wipro.sindhu.hms.dto.PatientDTO;

public interface PatientService {

	PatientDTO createPatient(PatientDTO patientDTO);
    PatientDTO getPatientById(Long id);
    List<PatientDTO> getAllPatients();
    void deletePatient(Long id);

    MedicalRecordDTO addMedicalRecord(Long patientId, MedicalRecordDTO recordDTO);
    MedicineDTO addMedicine(Long patientId, Long recordId, MedicineDTO medicienDTO);

}
