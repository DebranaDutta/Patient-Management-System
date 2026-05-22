package com.apt.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.apt.dto.DoctorDTO;

@FeignClient(name = "DoctorService")
public interface DoctorClient {
	@GetMapping("/api/doc/{id}")
	DoctorDTO getDoctorByid(@PathVariable(name = "id") String id);
}
