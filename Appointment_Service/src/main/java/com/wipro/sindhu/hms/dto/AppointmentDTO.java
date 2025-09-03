package com.wipro.sindhu.hms.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.wipro.sindhu.hms.enums.AppointmentStatus;


public class AppointmentDTO {
	
	private Long appointmentId;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private Long doctorId;
    private Long patientId;
    private AppointmentStatus status;

    private DoctorDTO doctor;
    private PatientDTO patient;
	public Long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}
	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public LocalTime getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(LocalTime appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	public Long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	public AppointmentStatus getStatus() {
		return status;
	}
	public void setStatus(AppointmentStatus status) {
		this.status = status;
	}
	public DoctorDTO getDoctor() {
		return doctor;
	}
	public void setDoctor(DoctorDTO doctor) {
		this.doctor = doctor;
	}
	public PatientDTO getPatient() {
		return patient;
	}
	public void setPatient(PatientDTO patient) {
		this.patient = patient;
	}
   
}
