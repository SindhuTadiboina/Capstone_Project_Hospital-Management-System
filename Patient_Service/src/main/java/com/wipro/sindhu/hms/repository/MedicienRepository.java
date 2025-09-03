package com.wipro.sindhu.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.sindhu.hms.entities.Medicine;


@Repository
public interface MedicienRepository extends JpaRepository<Medicine, Long> {

}
