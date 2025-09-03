package com.wipro.sindhu.hms.dto;

import java.time.LocalDate;
import java.util.List;

public class PatientDTO {
	
	private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String gender;
    private LocalDate dob;
    private Double weight;
    private Double height;
    private List<MedicalRecordDTO> medicalRecords;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public List<MedicalRecordDTO> getMedicalRecords() {
		return medicalRecords;
	}
	public void setMedicalRecords(List<MedicalRecordDTO> medicalRecords) {
		this.medicalRecords = medicalRecords;
	}

}
