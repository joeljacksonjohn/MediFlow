package com.mediserve.billingservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mediserve.billingservice.dto.MedicineDTO;
import com.mediserve.billingservice.dto.PrescriptionDTO;
import com.mediserve.billingservice.dto.UserDTO;
import com.mediserve.billingservice.entity.Bill;
import com.mediserve.billingservice.repository.BillRepository;

@Service
public class BillingService {
	private final BillRepository billRepository;
	private final RestTemplate restTemplate;

	public BillingService(BillRepository billRepository, RestTemplate restTemplate) {
		this.billRepository = billRepository;
		this.restTemplate = restTemplate;
	}

	// Create bill
	public Bill createBill(Bill bill) {
		// Validate patient exists in UserService
		try {
			UserDTO patient = restTemplate.getForObject("http://user-service/users/" + bill.getPatientId(),
					UserDTO.class);

			UserDTO doctor = restTemplate.getForObject("http://user-service/users/" + bill.getDoctorId(),
					UserDTO.class);

			bill.setPatientName(patient.getName());
			bill.setDoctorName(doctor.getName());

			// Fetch all prescriptions for this patient
			PrescriptionDTO[] prescriptions = restTemplate.getForObject(
					"http://pharmacy-service/pharmacy/prescriptions/patient/" + bill.getPatientId(),
					PrescriptionDTO[].class);

			double totalAmount = 0;

			// Calculate total amount
			for (PrescriptionDTO p : prescriptions) {
				// Fetch medicine details
				MedicineDTO medicine = restTemplate.getForObject(
						"http://pharmacy-service/pharmacy/medicines/" + p.getMedicineId(), MedicineDTO.class);

				totalAmount += medicine.getPrice() * p.getQuantity();
			}

			bill.setAmount(totalAmount);

			if (bill.getStatus() == null || bill.getStatus().isEmpty()) {
				bill.setStatus("UNPAID");
			}

		} catch (Exception e) {
			throw new RuntimeException("Patient or Doctor not found in UserService");
		}

		return billRepository.save(bill);
	}

	// Get all bills
	public List<Bill> getAllBills() {
		return billRepository.findAll();
	}

	// Get bills by patient
	public List<Bill> getBillsByPatient(Integer patientId) {
		return billRepository.findByPatientId(patientId);
	}

	// Update bill status
	public Bill updateBillStatus(Integer id, String status) {
		return billRepository.findById(id).map(bill -> {
			bill.setStatus(status);
			return billRepository.save(bill);
		}).orElseThrow(() -> new RuntimeException("Bill not found with id " + id));
	}

	// Get bill by ID
	public Bill getBillById(Integer id) {
		return billRepository.findById(id).orElseThrow(() -> new RuntimeException("Bill not found with id " + id));
	}

	// Delete bill
	public void deleteBill(Integer id) {
		if (!billRepository.existsById(id)) {
			throw new RuntimeException("Bill not found with id " + id);
		}
		billRepository.deleteById(id);
	}
}
