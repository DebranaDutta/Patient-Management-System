package com.ptn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptn.entity.Patient;
import com.ptn.service.PatientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
	@Autowired
	private PatientService patientService;

	@GetMapping("/all")
	public ResponseEntity<List<Patient>> getAllPatients() {
		List<Patient> patients = patientService.getAllPatients();
		return ResponseEntity.ok(patients);
	}

	@PostMapping("/create")
	public ResponseEntity<Patient> addPatient(@Valid @RequestBody Patient patient) {
		Patient spatient = patientService.addPatient(patient);
		return ResponseEntity.status(HttpStatus.CREATED).body(spatient);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Patient> updatePatient(@PathVariable(name = "id") String id, @RequestBody Patient patient) {
		Patient upatient = patientService.updatePatient(id, patient);
		return ResponseEntity.ok(upatient);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePatient(@PathVariable String id) {
		patientService.deletePatient(id);
		return ResponseEntity.ok("Patient data delted successfully");
	}

	@GetMapping("/{id}")
	public ResponseEntity<Patient> getPatientById(@PathVariable(name = "id") String id) {
		Patient patient = patientService.getPatientById(id);
		return ResponseEntity.ok(patient);
	}
}
