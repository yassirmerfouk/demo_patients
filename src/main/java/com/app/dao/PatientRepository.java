package com.app.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{

	@Query("SELECT p from Patient p WHERE p.firstName like :name")
	public Page<Patient> findByFirstName(@Param("name") String name, Pageable pageable);
}
