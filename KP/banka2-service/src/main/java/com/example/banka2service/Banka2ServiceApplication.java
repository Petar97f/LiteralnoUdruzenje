package com.example.banka2service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Banka2ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Banka2ServiceApplication.class, args);
	}

}
