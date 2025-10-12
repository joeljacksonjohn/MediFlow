package com.mediserve.appointmentservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mediserve.appointmentservice.entity.Appointment;
import com.mediserve.appointmentservice.service.AppointmentService;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
	private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // Create appointment
    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        return appointmentService.createAppointment(appointment);
    }

    // Get all appointments
    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    // Get appointment by ID
    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable Integer id) {
        return appointmentService.getAppointmentById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id " + id));
    }

    // Get appointments by patient
    @GetMapping("/patient/{patientId}")
    public List<Appointment> getAppointmentsByPatient(@PathVariable Integer patientId) {
        return appointmentService.getAppointmentsByPatient(patientId);
    }

    // Update appointment
    @PutMapping("/{id}")
    public Appointment updateAppointment(@PathVariable Integer id, @RequestBody Appointment appointment) {
        return appointmentService.updateAppointment(id, appointment);
    }

    // Delete appointment
    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Integer id) {
        appointmentService.deleteAppointment(id);
    }
}
