package com.apt.entity;

import java.time.LocalDateTime;

import com.apt.dto.DoctorDTO;
import com.apt.dto.PatientDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Schema(name = "AppointmentResponse", description = "Response object containing appointment details with doctor and patient information")
public class AppointmentResponse {
	@Schema(description = "Unique identifier of the appointment", example = "101")
	private Long id;

	@Schema(description = "Current status of the appointment", example = "Confirmed")
	private String status;

	@Schema(description = "Date and time of the appointment", example = "2026-05-27T10:30:00")
	private LocalDateTime appointmentDate;

	@Schema(description = "Patient details associated with the appointment")
	private PatientDTO patientDTO;

	@Schema(description = "Doctor details associated with the appointment")
	private DoctorDTO doctorDTO;
}
