package com.bankaservice.backend;

import com.bankaservice.backend.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.annotation.PostConstruct;

@EnableFeignClients
@SpringBootApplication
public class BankaServiceApplication {
	@Autowired
	CardService cardService;

	public static void main(String[] args) {

		SpringApplication.run(BankaServiceApplication.class, args);
	}
	@PostConstruct
	private void codes(){
		cardService.code();
	}

}
