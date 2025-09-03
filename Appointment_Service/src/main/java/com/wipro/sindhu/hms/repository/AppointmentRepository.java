package com.wipro.sindhu.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wipro.sindhu.hms.entities.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	
}
