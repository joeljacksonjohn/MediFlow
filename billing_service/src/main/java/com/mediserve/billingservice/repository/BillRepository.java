package com.mediserve.billingservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mediserve.billingservice.entity.Bill;

public interface BillRepository extends JpaRepository<Bill, Integer> {
	List<Bill> findByPatientId(Integer patientId);

	List<Bill> findByStatus(String status);

}
