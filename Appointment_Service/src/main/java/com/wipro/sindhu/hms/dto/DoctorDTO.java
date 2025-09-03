package com.wipro.sindhu.hms.dto;

import com.wipro.sindhu.hms.enums.SpecializationType;

public class DoctorDTO {

	private Long doctorId;
    private String name;
    private SpecializationType specialization;
    private String phoneNumber;
    private String email;
    private String gender;
    private Integer experienceYears;
	public Long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SpecializationType getSpecialization() {
		return specialization;
	}
	public void setSpecialization(SpecializationType specialization) {
		this.specialization = specialization;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getExperienceYears() {
		return experienceYears;
	}
	public void setExperienceYears(Integer experienceYears) {
		this.experienceYears = experienceYears;
	}
    
    
}
