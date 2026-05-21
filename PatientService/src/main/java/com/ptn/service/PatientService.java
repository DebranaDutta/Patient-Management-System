package com.ptn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptn.entity.Patient;
import com.ptn.repository.PatientRepository;

@Service
public interface PatientService {

	public List<Patient> getAllPatients();

	public Patient getPatientById(String id);

	public Patient addPatient(Patient patient);

	public Patient updatePatient(String id, Patient patient);

	public void deletePatient(String id);
}
