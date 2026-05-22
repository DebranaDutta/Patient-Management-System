package com.apt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apt.entity.Appointment;
import com.apt.entity.AppointmentResponse;
import com.apt.service.AppointmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	@PostMapping("/book")
	public ResponseEntity<Appointment> bookAppointment(@RequestBody @Valid Appointment appointment) {
		Appointment bookedAppointment = appointmentService.bookAppointment(appointment);
		return ResponseEntity.status(HttpStatus.CREATED).body(bookedAppointment);
	}

	/*@GetMapping("/all")
	public ResponseEntity<List<Appointment>> getAllAppointments() {
		List<Appointment> appointments = appointmentService.getAllAppointments();
		return ResponseEntity.status(HttpStatus.OK).body(appointments);
	}*/
	
	@GetMapping("/all")
	public ResponseEntity<List<AppointmentResponse>> getAllAppointments() {
		List<AppointmentResponse> appointmentResponses = appointmentService.getAllAppointmentsResponses();
		return ResponseEntity.status(HttpStatus.OK).body(appointmentResponses);
	}

	/*@GetMapping("/{id}")
	public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
		Appointment appointment = appointmentService.getAppointmentById(id);
		return ResponseEntity.ok(appointment);
	}*/
	
	@GetMapping("/{id}")
	public ResponseEntity<AppointmentResponse> getAppointmentById(@PathVariable Long id) {
		AppointmentResponse appointmentResponse = appointmentService.getAppointmentById(id);
		return ResponseEntity.ok(appointmentResponse);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
		Appointment updatedAppointment = appointmentService.updateAppointment(id, appointment);
		return ResponseEntity.ok(updatedAppointment);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> cancelAppointment(@PathVariable Long id) {
		appointmentService.cancelAppointment(id);
		return ResponseEntity.ok("Appointmnet cancelled");
	}
}
