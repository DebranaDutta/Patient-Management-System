package com.apt.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "appointment")
public class Appointment {
	@Id
   	@JsonProperty(value = "id")
    private Long id;

	@JsonProperty(value = "doctorId")
	@NotNull(message = "doctorId can not be null !!")
	@NotEmpty(message = "doctorId can not be empty !!")
    private String doctorId;
	
	@JsonProperty(value = "patientId")
	@NotNull(message = "patientId can not be null !!")
	@NotEmpty(message = "patientId can not be empty !!")
    private String patientId;
	
	@JsonProperty(value = "appointmentDate")
	@NotNull(message = "appointmentDate can not be null !!")
    private LocalDateTime appointmentDate;
	
	@JsonProperty(value = "status")
    private String status;
}
