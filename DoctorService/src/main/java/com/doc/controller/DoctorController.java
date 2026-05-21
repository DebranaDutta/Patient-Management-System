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

import com.doc.entity.Doctor;
import com.doc.service.DoctorService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/doc")
public class DoctorController {

	@Autowired
	private final DoctorService doctorService;

	public DoctorController(DoctorService doctorService) {
		this.doctorService = doctorService;
	}

	@PostMapping("/create")
	public ResponseEntity<Doctor> createDoctor(@Valid @RequestBody Doctor doctor) {
		Doctor sdoc = doctorService.addDoctor(doctor);
		return ResponseEntity.accepted().body(doctor);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Doctor>> getAllDoctors() {
		List<Doctor> doctors = doctorService.getAllDoctors();
		System.out.println(doctors.toString());
		return ResponseEntity.ok(doctors);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteDoctor(@PathVariable(name = "id") String id) {
		doctorService.deleteDoctor(id);
		return ResponseEntity.ok("Doctor data delted successfully");
	}

	@GetMapping("/{id}")
	public ResponseEntity<Doctor> getDoctorById(@PathVariable(name = "id") String id) {
		Doctor doctor = doctorService.getDoctorById(id);
		return ResponseEntity.ok().body(doctor);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Doctor> updateDoctor(@PathVariable(name = "id") String id, @RequestBody Doctor doctor) {
		Doctor udc = doctorService.updateDoctor(doctor, id);
		return ResponseEntity.ok(udc);
	}
}
