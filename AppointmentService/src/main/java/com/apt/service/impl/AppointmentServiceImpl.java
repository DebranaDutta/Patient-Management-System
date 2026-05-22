package com.apt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.apt.client.DoctorClient;
import com.apt.dto.DoctorDTO;
import com.apt.dto.PatientDTO;
import com.apt.entity.Appointment;
import com.apt.entity.AppointmentResponse;
import com.apt.exception.ResourceNotFoundException;
import com.apt.repository.AppointmentRepository;
import com.apt.service.AppointmentService;
import com.apt.util.IdGenerator;
import com.apt.util.VarConstant;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;
	private final DoctorClient doctorClient;
	private final RestTemplate restTemplate;

	public AppointmentServiceImpl(DoctorClient doctorClient, RestTemplate restTemplate) {
		this.doctorClient = doctorClient;
		this.restTemplate = restTemplate;
	}

	/************************** Internal Private Methods **************************/
	@CircuitBreaker(name = "DoctorService", fallbackMethod = "doctorFallback")
	@Retry(name = "DoctorService")
	private DoctorDTO getDoctorById(Appointment appointment) {
		DoctorDTO doctorDTO = doctorClient.getDoctorByid(appointment.getDoctorId());
		if (doctorDTO == null) {
			throw new ResourceNotFoundException(
					"Appointment Service : Doctor not found with given id: " + appointment.getDoctorId());
		} else {
			return doctorDTO;
		}
	}

	private DoctorDTO doctorFallback(Appointment appointment, Throwable t) {
		System.err.println("Doctor service call failed for id " + appointment.getDoctorId() + ": " + t.getMessage());

		// Return a safe default response
		DoctorDTO fallbackDoctor = new DoctorDTO();
		fallbackDoctor.setId(appointment.getDoctorId());
		fallbackDoctor.setName("Doctor Unavailable");
		fallbackDoctor.setSpecialization("N/A");
		fallbackDoctor.setEmail("N/A");
		fallbackDoctor.setPhoneNo("N/A");

		return fallbackDoctor;
	}

	private String createPatientServiceURL(Appointment appointment) {
		return VarConstant.HTTP_PATIENT_SERVICE_URI + appointment.getPatientId();
	}

	@CircuitBreaker(name = "PatientService", fallbackMethod = "patientFallback")
	@Retry(name = "PatientService")
	private PatientDTO getPatientById(Appointment appointment) {
		PatientDTO patientDTO = restTemplate.getForObject(createPatientServiceURL(appointment), PatientDTO.class);
		if (patientDTO == null) {
			throw new ResourceNotFoundException(
					"Appointment Service : Doctor not found with given id: " + appointment.getPatientId());
		} else {
			return patientDTO;
		}
	}

	// Fallback method must match signature + Throwable
	private PatientDTO patientFallback(Appointment appointment, Throwable t) {
		System.err.println("Patient service call failed for id " + appointment.getPatientId() + ": " + t.getMessage());

		PatientDTO fallbackPatient = new PatientDTO();
		fallbackPatient.setId(appointment.getPatientId());
		fallbackPatient.setName("Patient Unavailable");
		fallbackPatient.setAge(0);
		fallbackPatient.setGender("N/A");
		fallbackPatient.setEmail("N/A");
		fallbackPatient.setPhone("N/A");

		return fallbackPatient;
	}

	/************************** Internal Private Methods **************************/

	@Override
	public List<Appointment> getAllAppointments() {
		List<Appointment> appointments = appointmentRepository.findAll();
		return appointments;
	}

	@Override
	public List<AppointmentResponse> getAllAppointmentsResponses() {
		List<Appointment> appointments = appointmentRepository.findAll();
		List<AppointmentResponse> appointmentResponses = new ArrayList<>();
		for (Appointment appointment : appointments) {
			DoctorDTO doctorDTO = getDoctorById(appointment);
			PatientDTO patientDTO = getPatientById(appointment);
			AppointmentResponse appointmentResponse = new AppointmentResponse(appointment.getId(),
					appointment.getStatus(), appointment.getAppointmentDate(), patientDTO, doctorDTO);
			appointmentResponses.add(appointmentResponse);
		}
		return appointmentResponses;
	}

	@Override
	public AppointmentResponse getAppointmentById(Long id) {
		Appointment appointment = appointmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Appointment not exists with given Id !! "));
		DoctorDTO doctorDTO = getDoctorById(appointment);
		PatientDTO patientDTO = getPatientById(appointment);
		AppointmentResponse appointmentResponse = new AppointmentResponse(appointment.getId(), appointment.getStatus(),
				appointment.getAppointmentDate(), patientDTO, doctorDTO);
		return appointmentResponse;
	}

	@Override
	public Appointment bookAppointment(Appointment appointment) {
		Appointment savedAppointment = new Appointment();
		if (appointment != null) {
			savedAppointment.setId(IdGenerator.randomIdGenerator());
			savedAppointment.setDoctorId(appointment.getDoctorId());
			savedAppointment.setPatientId(appointment.getPatientId());
			savedAppointment.setAppointmentDate(appointment.getAppointmentDate());
			savedAppointment.setStatus(appointment.getStatus());
		}
		return appointmentRepository.save(savedAppointment);
	}

	@Override
	public Appointment updateAppointment(Long id, Appointment appointment) {
		Appointment existingAppointment = appointmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Appointment not exists with given Id !! "));
		if (existingAppointment != null) {
			existingAppointment.setDoctorId(appointment.getDoctorId());
			existingAppointment.setPatientId(appointment.getPatientId());
			existingAppointment.setAppointmentDate(appointment.getAppointmentDate());
			existingAppointment.setStatus(appointment.getStatus());

			return appointmentRepository.save(existingAppointment);
		}
		return null;
	}

	@Override
	public void cancelAppointment(Long id) {
		Appointment existingAppointment = appointmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Appointment not exists with given Id !! "));
		if (existingAppointment != null) {
			existingAppointment.setStatus("CANCELLED");
			appointmentRepository.save(existingAppointment);
		}
	}

}
