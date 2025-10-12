package com.mediserve.appointmentservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mediserve.appointmentservice.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findByPatientId(Integer patientId);
    List<Appointment> findByDoctorId(Integer doctorId); 

}
