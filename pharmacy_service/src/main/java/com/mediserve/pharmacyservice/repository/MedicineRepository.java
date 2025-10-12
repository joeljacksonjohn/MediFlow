package com.mediserve.pharmacyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mediserve.pharmacyservice.entity.Medicines;

public interface MedicineRepository extends JpaRepository<Medicines,Integer> {

}
