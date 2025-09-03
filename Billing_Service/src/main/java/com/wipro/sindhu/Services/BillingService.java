package com.wipro.sindhu.Services;

import java.util.List;

import com.wipro.sindhu.Entites.Billing;

public interface BillingService {
    Billing createBill(Billing billing);
    Billing getBillById(Long id);
    List<Billing> getBillsByPatient(Long patientId);
    List<Billing> getBillsByAppointment(Long appointmentId);
    List<Billing> getAllBills();
    Billing updateBillStatus(Long id, String status);
    void deleteBill(Long id);
}
