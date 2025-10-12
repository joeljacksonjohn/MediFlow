package com.mediserve.appointmentservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mediserve.appointmentservice.dto.UserDTO;
import com.mediserve.appointmentservice.entity.Appointment;
import com.mediserve.appointmentservice.repository.AppointmentRepository;

@Service
public class AppointmentService {
	private final AppointmentRepository appointmentRepository;
    private final RestTemplate restTemplate;

    public AppointmentService(AppointmentRepository appointmentRepository, RestTemplate restTemplate) {
        this.appointmentRepository = appointmentRepository;
        this.restTemplate = restTemplate;
    }

    // Create appointment
    public Appointment createAppointment(Appointment appointment) {
        // Validate patient exists in UserService
        try {
        	// Fetch patient and doctor info from UserService
        	UserDTO patient = restTemplate.getForObject(
        	    "http://user-service/users/" + appointment.getPatientId(), UserDTO.class
        	);

        	UserDTO doctor = restTemplate.getForObject(
        	    "http://user-service/users/" + appointment.getDoctorId(), UserDTO.class
        	);

        	// Set the names in the appointment
        	appointment.setPatientName(patient.getName());
        	appointment.setDoctorName(doctor.getName());

        } catch (Exception e) {
            throw new RuntimeException("Patient or Doctor not found in UserService");
        }

        return appointmentRepository.save(appointment);
    }

    // Get all appointments
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // Get appointment by ID
    public Optional<Appointment> getAppointmentById(Integer id) {
        return appointmentRepository.findById(id);
    }

    // Get appointments by patient
    public List<Appointment> getAppointmentsByPatient(Integer patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    // Update appointment
    public Appointment updateAppointment(Integer id, Appointment updatedAppointment) {
        return appointmentRepository.findById(id).map(appointment -> {
            appointment.setAppointmentDate(updatedAppointment.getAppointmentDate());
            appointment.setStatus(updatedAppointment.getStatus());
            return appointmentRepository.save(appointment);
        }).orElseThrow(() -> new RuntimeException("Appointment not found with id " + id));
    }

    // Delete appointment
    public void deleteAppointment(Integer id) {
        appointmentRepository.deleteById(id);
    }
}
