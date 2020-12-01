package com.bankaservice.backend.controller;

import com.bankaservice.backend.client.LuClient;
import com.bankaservice.backend.dto.UserDTO;
import com.bankaservice.backend.model.Card;
import com.bankaservice.backend.service.BankService;
import com.bankaservice.backend.service.CardService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class BankController {
    @Autowired
    private BankService bankService;
    @Autowired
    private CardService cardService;
    @Autowired
    private LuClient luClient;
    
    
    @GetMapping(value = "/getCardOwner")
	public UserDTO getUser(@RequestBody Card card) {
		
    	return luClient.getUser(card.getClientId());

		
	}
    
}
