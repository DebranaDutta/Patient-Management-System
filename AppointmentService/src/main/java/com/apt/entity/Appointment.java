package com.apt.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "Appointment", description = "Entity representing an appointment record")
public class Appointment {
	@Id
    @JsonProperty(value = "id")
    @Schema(description = "Unique identifier of the appointment", example = "101", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @JsonProperty(value = "doctorId")
    @NotNull(message = "doctorId can not be null !!")
    @NotEmpty(message = "doctorId can not be empty !!")
    @Schema(description = "Identifier of the doctor associated with the appointment", example = "d12345", requiredMode = Schema.RequiredMode.REQUIRED)
    private String doctorId;

    @JsonProperty(value = "patientId")
    @NotNull(message = "patientId can not be null !!")
    @NotEmpty(message = "patientId can not be empty !!")
    @Schema(description = "Identifier of the patient associated with the appointment", example = "p67890", requiredMode = Schema.RequiredMode.REQUIRED)
    private String patientId;

    @JsonProperty(value = "appointmentDate")
    @NotNull(message = "appointmentDate can not be null !!")
    @Schema(description = "Date and time of the appointment", example = "2026-05-27T10:30:00", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime appointmentDate;

    @JsonProperty(value = "status")
    @Schema(description = "Current status of the appointment", example = "Confirmed")
    private String status;
}
