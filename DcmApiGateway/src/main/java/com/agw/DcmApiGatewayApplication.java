package com.agw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DcmApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(DcmApiGatewayApplication.class, args);
	}

}
