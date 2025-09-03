package com.wipro.sindhu.hms.services;

import java.util.List;

import com.wipro.sindhu.hms.dto.DoctorDTO;

public interface DoctorService {

	DoctorDTO createDoctor(DoctorDTO doctorDTO);
    DoctorDTO getDoctorById(Long id);
    List<DoctorDTO> getAllDoctors();
    DoctorDTO updateDoctor(Long id, DoctorDTO doctorDTO);
    void deleteDoctor(Long id);
}
