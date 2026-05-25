package com.apt.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	Logger LOGGER = LoggerFactory.getLogger(AppointmentController.class);

	/************************** bookAppointment **************************/
	@PostMapping("/book")
	public ResponseEntity<Appointment> bookAppointment(@RequestBody @Valid Appointment appointment) {
		Appointment bookedAppointment = appointmentService.bookAppointment(appointment);
		return ResponseEntity.status(HttpStatus.CREATED).body(bookedAppointment);
	}
	/************************** bookAppointment **************************/
	
	
	/************************** getAllAppointments **************************/
	@CircuitBreaker(name = "circuitBreakerDoctorPatientAll", fallbackMethod = "fallbackMethodDoctorPatientAll")
	@Retry(name = "retryDoctorPatientAll", fallbackMethod = "fallbackMethodDoctorPatientAll")
	@GetMapping("/all")
	public ResponseEntity<List<AppointmentResponse>> getAllAppointments() {
		List<AppointmentResponse> appointmentResponses = appointmentService.getAllAppointmentsResponses();
		return ResponseEntity.status(HttpStatus.OK).body(appointmentResponses);
	}
	
	public ResponseEntity<List<AppointmentResponse>> fallbackMethodDoctorPatientAll(Exception ex) {
		LOGGER.info("fallbackMethodDoctorPatientAll() is implemented because service down : " + ex.getMessage());
		AppointmentResponse response1 = AppointmentResponse.builder().id(null).status(null).appointmentDate(null)
				.patientDTO(null).doctorDTO(null).build();
		AppointmentResponse response2 = AppointmentResponse.builder().id(null).status(null).appointmentDate(null)
				.patientDTO(null).doctorDTO(null).build();
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(Arrays.asList(response1,response2));
	}
	/************************** getAllAppointments **************************/
	
	
	/************************** getAppointmentById **************************/
	@CircuitBreaker(name = "circuitBreakerDoctorPatientById", fallbackMethod = "fallbackMethodDoctorPatientById")
	@Retry(name = "retryDoctorPatientById", fallbackMethod = "fallbackMethodDoctorPatientById")
	@GetMapping("/{id}")
	public ResponseEntity<AppointmentResponse> getAppointmentById(@PathVariable Long id) {
		AppointmentResponse appointmentResponse = appointmentService.getAppointmentResponseById(id);
		return ResponseEntity.ok(appointmentResponse);
	}

	public ResponseEntity<AppointmentResponse> fallbackMethodDoctorPatientById(Long id, Exception ex) {
		LOGGER.info("fallbackMethodDoctorPatientById() is implemented because service down : " + ex.getMessage());
		AppointmentResponse response = AppointmentResponse.builder().id(id).status("Unknown").appointmentDate(null)
				.patientDTO(null).doctorDTO(null).build();
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
	}
	/************************** getAppointmentById **************************/
	
	
	/************************** updateAppointment **************************/
	@PutMapping("/{id}")
	public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
		Appointment updatedAppointment = appointmentService.updateAppointment(id, appointment);
		return ResponseEntity.ok(updatedAppointment);
	}
	/************************** updateAppointment **************************/
	
	
	/************************** cancelAppointment **************************/
	@DeleteMapping("/{id}")
	public ResponseEntity<String> cancelAppointment(@PathVariable Long id) {
		appointmentService.cancelAppointment(id);
		return ResponseEntity.ok("Appointmnet cancelled");
	}
	/************************** cancelAppointment **************************/
}
