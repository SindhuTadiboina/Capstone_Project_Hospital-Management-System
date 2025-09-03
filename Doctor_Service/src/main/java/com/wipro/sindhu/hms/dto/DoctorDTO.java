package com.wipro.sindhu.hms.dto;

import com.wipro.sindhu.hms.enums.Gender;

public class DoctorDTO {
	
	private Long id;
    private String name;
    private String specialization;
    private String phoneNumber;
    private String email;
    private Gender gender;
    private Integer experienceYears;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
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
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Integer getExperienceYears() {
		return experienceYears;
	}
	public void setExperienceYears(Integer experienceYears) {
		this.experienceYears = experienceYears;
	}

}
