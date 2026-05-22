package com.apt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.apt.entity.Appointment;
import com.apt.entity.AppointmentResponse;

@Service
public interface AppointmentService {
	public List<Appointment> getAllAppointments();
	
	public List<AppointmentResponse> getAllAppointmentsResponses();

	public AppointmentResponse getAppointmentById(Long id);

	public Appointment bookAppointment(Appointment appointment);

	public Appointment updateAppointment(Long id, Appointment appointment);

	public void cancelAppointment(Long id);
}
