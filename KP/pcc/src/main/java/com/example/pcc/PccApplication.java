package com.example.pcc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class PccApplication {

	public static void main(String[] args) {
		SpringApplication.run(PccApplication.class, args);
	}

}
