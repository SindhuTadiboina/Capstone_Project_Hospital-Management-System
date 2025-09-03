package com.wipro.sindhu.hms.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.wipro.sindhu.hms.dto.DoctorDTO;
import com.wipro.sindhu.hms.entities.Doctor;
import com.wipro.sindhu.hms.enums.SpecializationType;
import com.wipro.sindhu.hms.exceptions.DoctorNotFoundException;
import com.wipro.sindhu.hms.exceptions.ResourceNotFoundException;
import com.wipro.sindhu.hms.repository.DoctorRepository;

@Service
public class DoctorServiceImpl implements DoctorService { 
	
	 private final DoctorRepository doctorRepository;

	    public DoctorServiceImpl(DoctorRepository doctorRepository) {
	        this.doctorRepository = doctorRepository;
	    }

	    @Override
	    public DoctorDTO createDoctor(DoctorDTO doctorDTO) {
	        Doctor doctor = new Doctor();
	        doctor.setName(doctorDTO.getName());
	        doctor.setEmail(doctorDTO.getEmail());
	        doctor.setPhoneNumber(doctorDTO.getPhoneNumber());
	        doctor.setGender(doctorDTO.getGender());
	        doctor.setSpecialization(doctorDTO.getSpecialization());
	        doctor.setExperienceYears(doctorDTO.getExperienceYears());
	        return mapToDTO(doctorRepository.save(doctor));
	    }

	    @Override
	    public DoctorDTO getDoctorById(Long id) {
	        Doctor doctor = doctorRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
	        return mapToDTO(doctor);
	    }

	    @Override
	    public List<DoctorDTO> getAllDoctors() {
	        return doctorRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	    }

	    @Override
	    public DoctorDTO updateDoctor(Long id, DoctorDTO doctorDTO) {
	        Doctor doctor = doctorRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));

	        doctor.setName(doctorDTO.getName());
	        doctor.setEmail(doctorDTO.getEmail());
	        doctor.setPhoneNumber(doctorDTO.getPhoneNumber());
	        doctor.setGender(doctorDTO.getGender());
	        doctor.setSpecialization(doctorDTO.getSpecialization());
	        doctor.setExperienceYears(doctorDTO.getExperienceYears());

	        return mapToDTO(doctorRepository.save(doctor));
	    }

	    @Override
	    public void deleteDoctor(Long id) {
	        if (!doctorRepository.existsById(id)) {
	            throw new ResourceNotFoundException("Doctor not found with id: " + id);
	        }
	        doctorRepository.deleteById(id);
	    }

	    private DoctorDTO mapToDTO(Doctor doctor) {
	        DoctorDTO dto = new DoctorDTO();
	        dto.setId(doctor.getId());
	        dto.setName(doctor.getName());
	        dto.setEmail(doctor.getEmail());
	        dto.setPhoneNumber(doctor.getPhoneNumber());
	        dto.setGender(doctor.getGender());
	        dto.setSpecialization(doctor.getSpecialization());
	        dto.setExperienceYears(doctor.getExperienceYears());
	        return dto;
	    }
}
