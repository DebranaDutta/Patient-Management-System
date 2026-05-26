package com.doc.controller;

import java.util.List;

import javax.print.Doc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doc.entity.Doctor;
import com.doc.service.DoctorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/doc")
@Tag(name="Doctor API", description = "Doctor service endpoints")
public class DoctorController {

	@Autowired
	private final DoctorService doctorService;

	public DoctorController(DoctorService doctorService) {
		this.doctorService = doctorService;
	}

	/************************** createDoctor **************************/
	@Operation(summary = "Create a new doctor", description = "Adds a new doctor record to the system")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "202", description = "Doctor created successfully"),
	        @ApiResponse(responseCode = "400", description = "Invalid input data")
	    })
	@PostMapping("/create")
	public ResponseEntity<Doctor> createDoctor(@Valid @RequestBody Doctor doctor) {
		Doctor sdoc = doctorService.addDoctor(doctor);
		return ResponseEntity.accepted().body(doctor);
	}
	/************************** createDoctor **************************/
	
	
	/************************** getAllDoctors **************************/
	@Operation(summary = "Get all doctors", description = "Fetches a list of all doctors")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of doctors returned")
    })
	@GetMapping("/getAll")
	public ResponseEntity<List<Doctor>> getAllDoctors() {
		List<Doctor> doctors = doctorService.getAllDoctors();
		System.out.println(doctors.toString());
		return ResponseEntity.ok(doctors);
	}
	/************************** getAllDoctors **************************/
	
	
	/************************** getDoctorById **************************/
	@Operation(summary = "Get doctor by ID", description = "Fetches doctor details using doctor ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Doctor details returned"),
        @ApiResponse(responseCode = "404", description = "Doctor not found")
    })
	@GetMapping("/{id}")
	public ResponseEntity<Doctor> getDoctorById(@PathVariable(name = "id") String id) {
		Doctor doctor = doctorService.getDoctorById(id);
		return ResponseEntity.ok().body(doctor);
	}
	/************************** getDoctorById **************************/
	
	
	/************************** deleteDoctor **************************/
	@Operation(summary = "Delete doctor by ID", description = "Deletes a doctor record using doctor ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Doctor deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Doctor not found")
    })
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteDoctor(@PathVariable(name = "id") String id) {
		doctorService.deleteDoctor(id);
		return ResponseEntity.ok("Doctor data delted successfully");
	}
	/************************** deleteDoctor **************************/


	/************************** updateDoctor **************************/
	@Operation(summary = "Update doctor by ID", description = "Updates doctor details using doctor ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Doctor updated successfully"),
        @ApiResponse(responseCode = "404", description = "Doctor not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
	@PutMapping("/{id}")
	public ResponseEntity<Doctor> updateDoctor(@PathVariable(name = "id") String id, @RequestBody Doctor doctor) {
		Doctor udc = doctorService.updateDoctor(doctor, id);
		return ResponseEntity.ok(udc);
	}
	/************************** updateDoctor **************************/
}
