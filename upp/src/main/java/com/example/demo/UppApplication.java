package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class UppApplication {

	public static void main(String[] args) {
		SpringApplication.run(UppApplication.class, args);
	}

}
