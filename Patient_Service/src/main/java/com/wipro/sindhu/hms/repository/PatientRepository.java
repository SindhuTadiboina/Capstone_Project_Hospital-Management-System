package com.wipro.sindhu.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.sindhu.hms.entities.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

}
