package com.doc.entity;


import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "doctors")
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@JsonProperty("id")
	@NotNull(message = "Id can not be null")
	@NotEmpty(message = "Id can not be empty")
	private String id;
	
	@JsonProperty(value = "name", required = true)
	@NotNull(message = "Name can not be null")
	@NotEmpty(message = "Name can not be empty")
	private String name;
	
	@JsonProperty(value = "specialization", required = true)
	@NotNull(message = "Specialization can not be null")
	@NotEmpty(message = "Specialization can not be null")
	private String specialization;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty(value = "phoneNo", required = true)
	@NotNull(message = "phone can not be null")
	@NotEmpty(message = "phone can not be null")
	private String phone;

	@Override
	public String toString() {
		return "Doctor [id=" + id + ", name=" + name + ", specialization=" + specialization + ", email=" + email
				+ ", phone=" + phone + "]";
	}
	
}
