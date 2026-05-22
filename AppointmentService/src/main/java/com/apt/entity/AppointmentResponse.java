package com.apt.entity;

import java.time.LocalDateTime;

import com.apt.dto.DoctorDTO;
import com.apt.dto.PatientDTO;

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
public class AppointmentResponse {
	private Long id;
	private String status;
	private LocalDateTime appointmentDate;
	private PatientDTO patientDTO;
	private DoctorDTO doctorDTO;
}
