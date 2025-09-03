package com.wipro.sindhu.hms.services;

import java.util.List;
import com.wipro.sindhu.hms.dto.AppointmentDTO;


public interface AppointmentService {

	AppointmentDTO createAppointment(AppointmentDTO dto);
    AppointmentDTO updateAppointment(Long id, AppointmentDTO dto);
    AppointmentDTO getAppointmentById(Long id);
    List<AppointmentDTO> getAllAppointments();
    void deleteAppointment(Long id);
}
