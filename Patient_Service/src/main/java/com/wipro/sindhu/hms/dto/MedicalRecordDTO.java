package com.wipro.sindhu.hms.dto;

import java.time.LocalDateTime;
import java.util.List;

public class MedicalRecordDTO {
	
	private Long id;
    private String whoDoctor;
    private String treatedAt;
    private LocalDateTime revisitingTime;
    private List<MedicineDTO> medicines;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getWhoDoctor() {
		return whoDoctor;
	}
	public void setWhoDoctor(String whoDoctor) {
		this.whoDoctor = whoDoctor;
	}
	public String getTreatedAt() {
		return treatedAt;
	}
	public void setTreatedAt(String treatedAt) {
		this.treatedAt = treatedAt;
	}
	public LocalDateTime getRevisitingTime() {
		return revisitingTime;
	}
	public void setRevisitingTime(LocalDateTime revisitingTime) {
		this.revisitingTime = revisitingTime;
	}
	public List<MedicineDTO> getMedicines() {
		return medicines;
	}
	public void setMedicines(List<MedicineDTO> medicines) {
		this.medicines = medicines;
	}

}
