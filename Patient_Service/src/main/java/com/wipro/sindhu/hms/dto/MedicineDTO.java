package com.wipro.sindhu.hms.dto;

public class MedicineDTO {
	
	private Long id;
    private String medicienName;
    private Integer quantity;
    private String dosageTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMedicienName() {
		return medicienName;
	}
	public void setMedicienName(String medicienName) {
		this.medicienName = medicienName;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getDosageTime() {
		return dosageTime;
	}
	public void setDosageTime(String dosageTime) {
		this.dosageTime = dosageTime;
	}

}
