package com.ptn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptn.entity.Patient;
import com.ptn.exception.ResourceNotFoundException;
import com.ptn.repository.PatientRepository;
import com.ptn.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientRepository patientRepository;

	@Override
	public List<Patient> getAllPatients() {
		List<Patient> patients = patientRepository.findAll();
		return patients;
	}

	@Override
	public Patient getPatientById(String id) {
		Patient patient = patientRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Doctor with given id is not found on server !! : " + id));
		return patient;
	}

	@Override
	public Patient addPatient(Patient patient) {
		return patientRepository.save(patient);
	}

	@Override
	public Patient updatePatient(String id, Patient patient) {
		Patient existing = patientRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Doctor with given id is not found on server !! : " + id));
		if (existing != null) {
			existing.setName(patient.getName());
			existing.setAge(patient.getAge());
			existing.setGender(patient.getGender());
			existing.setEmail(patient.getEmail());
			existing.setPhone(patient.getPhone());
			return patientRepository.save(existing);
		}
		return null;
	}

	@Override
	public void deletePatient(String id) {
		  patientRepository.deleteById(id);
	}

}
