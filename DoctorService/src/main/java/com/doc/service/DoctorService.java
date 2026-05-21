package com.doc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.doc.entity.Doctor;

@Service
public interface DoctorService {
	public List<Doctor> getAllDoctors();
	
	public Doctor getDoctorById(String id);
	
	public Doctor addDoctor(Doctor doctor);
	
	public Doctor updateDoctor(Doctor doctor, String id);
	
	public void deleteDoctor(String id);
}
