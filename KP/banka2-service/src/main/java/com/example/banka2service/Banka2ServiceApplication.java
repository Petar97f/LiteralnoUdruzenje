package com.example.banka2service;

import com.example.banka2service.service.CardService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.annotation.PostConstruct;

@EnableFeignClients
@SpringBootApplication
public class Banka2ServiceApplication {
	@Autowired
	CardService cardService;
	public static void main(String[] args) {
		SpringApplication.run(Banka2ServiceApplication.class, args);
	}

	@PostConstruct
	private void codes(){
		cardService.code();
	}

}
