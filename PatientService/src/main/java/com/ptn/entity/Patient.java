package com.ptn.entity;

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
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "patients")
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@NotNull(message = "id can not be null")
	@JsonProperty(value = "id")
	private String id;

	@JsonProperty(value = "name")
	@NotNull(message = "name can not be null")
	@NotEmpty(message = "name can not be empty")
	private String name;

	@JsonProperty(value = "age")
	@NotNull(message = "age can not be null")
	private int age;

	@JsonProperty(value = "gender")
	@NotNull(message = "gender can not be null")
	@NotEmpty(message = "gender can not be empty")
	private String gender;

	@JsonProperty(value = "email")
	private String email;

	@JsonProperty(value = "phone")
	@NotNull(message = "phone can not be null")
	@NotEmpty(message = "phone can not be empty")
	private String phone;
}
