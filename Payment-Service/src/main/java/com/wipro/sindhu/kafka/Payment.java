package com.wipro.sindhu.kafka;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {
    private String userEmail;
    private double amount;
    private String status;  // SUCCESS or FAIL
    private String message;
    
 // No-arg constructor
    public Payment() {
    }

    // All-arg constructor
    public Payment(String userEmail, double amount, String status, String message) {
        this.userEmail = userEmail;
        this.amount = amount;
        this.status = status;
        this.message = message;
    }
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
    
    
}
