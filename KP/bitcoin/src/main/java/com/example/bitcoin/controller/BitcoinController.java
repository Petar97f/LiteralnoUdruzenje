package com.example.bitcoin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(value = "/bitcoin")
public class BitcoinController {

	private static final Logger logger = LoggerFactory.getLogger(BitcoinController.class);
	
	@PostMapping("/create")
	public ResponseEntity<?> createPayment() {
		logger.info("INITIATED | Creating payment");
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
