package com.ptn.entity;

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
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "patients")
@Schema(name = "Patient", description = "Patient entity representing patient details")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@NotNull(message = "id can not be null")
	@JsonProperty(value = "id")
	@Schema(description = "Unique identifier of the patient", example = "p12345", requiredMode = Schema.RequiredMode.REQUIRED)
	private String id;

	@JsonProperty(value = "name")
	@NotNull(message = "name can not be null")
	@NotEmpty(message = "name can not be empty")
	@Schema(description = "Full name of the patient", example = "Jane Doe", requiredMode = Schema.RequiredMode.REQUIRED)
	private String name;

	@JsonProperty(value = "age")
	@NotNull(message = "age can not be null")
	@Schema(description = "Age of the patient", example = "35", requiredMode = Schema.RequiredMode.REQUIRED)
	private int age;

	@JsonProperty(value = "gender")
	@NotNull(message = "gender can not be null")
	@NotEmpty(message = "gender can not be empty")
	@Schema(description = "Gender of the patient", example = "Female", requiredMode = Schema.RequiredMode.REQUIRED)
	private String gender;

	@JsonProperty(value = "email")
	@Schema(description = "Email address of the patient", example = "jane.doe@example.com")
	private String email;

	@JsonProperty(value = "phone")
	@NotNull(message = "phone can not be null")
	@NotEmpty(message = "phone can not be empty")
	@Schema(description = "Phone number of the patient", example = "+91-9876543210", requiredMode = Schema.RequiredMode.REQUIRED)
	private String phone;
}