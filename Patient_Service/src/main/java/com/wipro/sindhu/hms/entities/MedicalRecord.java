package com.wipro.sindhu.hms.entities;


import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "medical_record")
public class MedicalRecord {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    private String whoDoctor;
    private String treatedAt;
    private LocalDateTime revisitingTime;

    @OneToMany(mappedBy = "medicalRecord", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Medicine> medicines;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
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

	public List<Medicine> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<Medicine> medicines) {
		this.medicines = medicines;
	}
 
}
