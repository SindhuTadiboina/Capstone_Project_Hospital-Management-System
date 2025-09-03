package com.wipro.sindhu.hms.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wipro.sindhu.hms.dto.AppointmentDTO;
import com.wipro.sindhu.hms.dto.DoctorDTO;
import com.wipro.sindhu.hms.dto.PatientDTO;
import com.wipro.sindhu.hms.entities.Appointment;
import com.wipro.sindhu.hms.exceptions.ResourceNotFoundException;
import com.wipro.sindhu.hms.feign.DoctorClient;
import com.wipro.sindhu.hms.feign.PatientClient;
import com.wipro.sindhu.hms.repository.AppointmentRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	 	@Autowired
	    private AppointmentRepository repo;

	    @Autowired
	    private DoctorClient doctorClient;

	    @Autowired
	    private PatientClient patientClient;

	    private AppointmentDTO mapToDTO(Appointment appointment) {
	        AppointmentDTO dto = new AppointmentDTO();
	        dto.setAppointmentId(appointment.getAppointmentId());
	        dto.setAppointmentDate(appointment.getAppointmentDate());
	        dto.setAppointmentTime(appointment.getAppointmentTime());
	        dto.setDoctorId(appointment.getDoctorId());
	        dto.setPatientId(appointment.getPatientId());
	        dto.setStatus(appointment.getStatus());

	        DoctorDTO doctor = doctorClient.getDoctorById(appointment.getDoctorId());
	        dto.setDoctor(doctor);

	        PatientDTO patient = patientClient.getPatientById(appointment.getPatientId());
	        dto.setPatient(patient);

	        return dto;
	    }

	    private Appointment mapToEntity(AppointmentDTO dto) {
	        Appointment appointment = new Appointment();
	        appointment.setAppointmentId(dto.getAppointmentId());
	        appointment.setAppointmentDate(dto.getAppointmentDate());
	        appointment.setAppointmentTime(dto.getAppointmentTime());
	        appointment.setDoctorId(dto.getDoctorId());
	        appointment.setPatientId(dto.getPatientId());
	        appointment.setStatus(dto.getStatus());
	        return appointment;
	    }

	    @Override
	    public AppointmentDTO createAppointment(AppointmentDTO dto) {
	        Appointment appointment = mapToEntity(dto);
	        Appointment saved = repo.save(appointment);
	        return mapToDTO(saved);
	    }

	    @Override
	    public AppointmentDTO updateAppointment(Long id, AppointmentDTO dto) {
	        Appointment appointment = repo.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));
	        appointment.setStatus(dto.getStatus());
	        appointment.setAppointmentDate(dto.getAppointmentDate());
	        appointment.setAppointmentTime(dto.getAppointmentTime());
	        Appointment updated = repo.save(appointment);
	        return mapToDTO(updated);
	    }

	    @Override
	    public AppointmentDTO getAppointmentById(Long id) {
	        Appointment appointment = repo.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));
	        return mapToDTO(appointment);
	    }

	    @Override
	    public List<AppointmentDTO> getAllAppointments() {
	        return repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	    }

	    @Override
	    public void deleteAppointment(Long id) {
	        Appointment appointment = repo.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));
	        repo.delete(appointment);
	    }
}
