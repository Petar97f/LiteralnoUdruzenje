package com.bankaservice.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableFeignClients
@SpringBootApplication
public class BankaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankaServiceApplication.class, args);
	}

}
