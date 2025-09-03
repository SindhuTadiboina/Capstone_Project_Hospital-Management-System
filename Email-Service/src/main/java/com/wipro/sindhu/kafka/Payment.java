package com.wipro.sindhu.kafka;

public class Payment {
    private String userEmail;
    private double amount;
    private String status;
    private String message;

    public Payment() {
    }

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
