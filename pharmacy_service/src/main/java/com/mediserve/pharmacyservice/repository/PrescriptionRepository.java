package com.mediserve.pharmacyservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mediserve.pharmacyservice.entity.Prescriptions;

public interface PrescriptionRepository extends JpaRepository<Prescriptions,Integer> {
	List<Prescriptions> findByPatientId(Integer patientId);
	List<Prescriptions> findByDoctorId(Integer doctorId);
}
