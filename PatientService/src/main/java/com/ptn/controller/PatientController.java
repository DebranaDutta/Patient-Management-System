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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/patients")
@Tag(name = "Patient API", description = "Patient service endpoints")
public class PatientController {
	@Autowired
	private PatientService patientService;

	/************************** getAllPatients **************************/
	@Operation(summary = "Get all patients", description = "Fetches a list of all patients")
	@ApiResponses(value = {	@ApiResponse(
									responseCode = "200", 
									description = "List of patients returned", 
									content = @Content(mediaType = "application/json", 
									schema = @Schema(implementation = Patient.class))) })
	@GetMapping("/all")
	public ResponseEntity<List<Patient>> getAllPatients() {
		List<Patient> patients = patientService.getAllPatients();
		return ResponseEntity.ok(patients);
	}

	/************************** getAllPatients **************************/

	
	/************************** addPatient **************************/
	@Operation(summary = "Add a new patient", description = "Creates a new patient record")
	@ApiResponses(value = {	@ApiResponse(
									responseCode = "201", 
									description = "Patient created successfully", 
									content = @Content(mediaType = "application/json", 
									schema = @Schema(implementation = Patient.class))),
							@ApiResponse(
									responseCode = "400", 
									description = "Invalid input data", 
									content = @Content(mediaType = "application/json", 
									schema = @Schema(implementation = com.ptn.payload.ApiResponse.class))) })
	@PostMapping("/create")
	public ResponseEntity<Patient> addPatient(@Valid @RequestBody Patient patient) {
		Patient spatient = patientService.addPatient(patient);
		return ResponseEntity.status(HttpStatus.CREATED).body(spatient);
	}

	/************************** addPatient **************************/

	
	/************************** updatePatient **************************/
	@Operation(summary = "Update patient by ID", description = "Updates patient details using patient ID")
	@ApiResponses(value = {	@ApiResponse(
									responseCode = "200", 
									description = "Patient updated successfully", 
									content = @Content(mediaType = "application/json", 
									schema = @Schema(implementation = Patient.class))),
							@ApiResponse(
									responseCode = "404", 
									description = "Patient not found", 
									content = @Content(mediaType = "application/json", 
									schema = @Schema(implementation = com.ptn.payload.ApiResponse.class))) })
	@PutMapping("/{id}")
	public ResponseEntity<Patient> updatePatient(@PathVariable(name = "id") String id, @RequestBody Patient patient) {
		Patient upatient = patientService.updatePatient(id, patient);
		return ResponseEntity.ok(upatient);
	}

	/************************** updatePatient **************************/

	
	/************************** deletePatient **************************/
	@Operation(summary = "Delete patient by ID", description = "Deletes a patient record using patient ID")
	@ApiResponses(value = {	@ApiResponse(
									responseCode = "200", 
									description = "Patient deleted successfully", 
									content = @Content(mediaType = "application/json", 
									schema = @Schema(implementation = Patient.class))),
							@ApiResponse(
									responseCode = "404", 
									description = "Patient not found", 
									content = @Content(mediaType = "application/json", 
									schema = @Schema(implementation = com.ptn.payload.ApiResponse.class))) })
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePatient(@PathVariable String id) {
		patientService.deletePatient(id);
		return ResponseEntity.ok("Patient data delted successfully");
	}

	/************************** deletePatient **************************/

	
	/************************** getPatientById **************************/
	@Operation(summary = "Get patient by ID", description = "Fetches patient details using patient ID")
	@ApiResponses(value = {	@ApiResponse(
									responseCode = "200", 
									description = "Patient details returned", 
									content = @Content(mediaType = "application/json", 
									schema = @Schema(implementation = Patient.class))),
							@ApiResponse(
									responseCode = "404", 
									description = "Patient not found", 
									content = @Content(mediaType = "application/json", 
									schema = @Schema(implementation = com.ptn.payload.ApiResponse.class))) })
	@GetMapping("/{id}")
	public ResponseEntity<Patient> getPatientById(@PathVariable(name = "id") String id) {
		Patient patient = patientService.getPatientById(id);
		return ResponseEntity.ok(patient);
	}
	/************************** getPatientById **************************/
}
