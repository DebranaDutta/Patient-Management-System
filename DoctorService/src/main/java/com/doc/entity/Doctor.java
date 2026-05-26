package com.doc.entity;


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
@Schema(name = "Doctor", description = "Doctor entity representing doctor details")
public class Doctor {
	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonProperty("id")
    @Schema(description = "Unique identifier of the doctor", example = "d12345", required = true)
    @NotNull(message = "Id can not be null")
    @NotEmpty(message = "Id can not be empty")
    private String id;

    @JsonProperty(value = "name", required = true)
    @Schema(description = "Full name of the doctor", example = "Dr. John Doe", required = true)
    @NotNull(message = "Name can not be null")
    @NotEmpty(message = "Name can not be empty")
    private String name;

    @JsonProperty(value = "specialization", required = true)
    @Schema(description = "Doctor's specialization field", example = "Cardiology", required = true)
    @NotNull(message = "Specialization can not be null")
    @NotEmpty(message = "Specialization can not be empty")
    private String specialization;

    @JsonProperty("email")
    @Schema(description = "Doctor's email address", example = "doctor@example.com")
    private String email;

    @JsonProperty(value = "phoneNo", required = true)
    @Schema(description = "Doctor's phone number", example = "+91-9876543210", required = true)
    @NotNull(message = "Phone can not be null")
    @NotEmpty(message = "Phone can not be empty")
    private String phone;

	@Override
	public String toString() {
		return "Doctor [id=" + id + ", name=" + name + ", specialization=" + specialization + ", email=" + email
				+ ", phone=" + phone + "]";
	}
	
}
