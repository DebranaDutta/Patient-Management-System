package com.doc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doc.entity.Doctor;
import com.doc.exception.ResourceNotFoundException;
import com.doc.repository.DoctorRepository;
import com.doc.service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {

	private final DoctorRepository repository;

	public DoctorServiceImpl(DoctorRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Doctor> getAllDoctors() {
		List<Doctor> doctors = repository.findAll();
		return doctors;
	}

	@Override
	public Doctor getDoctorById(String id) {
		Doctor doctor = repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Doctor with given id is not found on server !! : " + id));
		return doctor;
	}

	@Override
	public Doctor addDoctor(Doctor doctor) {
		Doctor savedDoc = repository.save(doctor);
		return savedDoc;
	}

	@Override
	public Doctor updateDoctor(Doctor doctor, String id) {
		
		return null;
	}

	@Override
	public void deleteDoctor(String id) {
		repository.deleteById(id);
	}

}
