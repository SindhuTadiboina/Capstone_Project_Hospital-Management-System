package com.wipro.sindhu.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wipro.sindhu.hms.entities.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	
}
