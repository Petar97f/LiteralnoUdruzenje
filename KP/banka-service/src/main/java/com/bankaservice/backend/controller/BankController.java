package com.bankaservice.backend.controller;

import com.bankaservice.backend.client.LuClient;
import com.bankaservice.backend.dto.PaymentDTO;
import com.bankaservice.backend.dto.PaymentRequestDTO;
import com.bankaservice.backend.dto.UserDTO;
import com.bankaservice.backend.model.Card;
import com.bankaservice.backend.model.Payment;
import com.bankaservice.backend.service.BankService;
import com.bankaservice.backend.service.CardService;


import com.bankaservice.backend.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class BankController {
    @Autowired
    private BankService bankService;
    @Autowired
    private CardService cardService;
    @Autowired
    private LuClient luClient;
    @Autowired
    private PaymentService paymentService;
    
    
    @GetMapping(value = "/getCardOwner")
	public UserDTO getUser(@RequestBody Card card) {
		
    	return luClient.getUser(card.getClientId());

		
	}

    @PostMapping(value = "/BankPay")
    public PaymentDTO BankPay(@RequestBody PaymentRequestDTO paymentRequestDTO){
        PaymentDTO paymentDTO=new PaymentDTO();
        Payment payment=new Payment();
        if(paymentRequestDTO.getAmount() == null || paymentRequestDTO.getErrorUrl().isEmpty() || paymentRequestDTO.getFailedUrl().isEmpty() || paymentRequestDTO.getMerchantId().isEmpty()
        || paymentRequestDTO.getSuccessUrl().isEmpty() || paymentRequestDTO.getMerchantOrderId() == null || paymentRequestDTO.getMerchantTimestamp() == null || paymentRequestDTO.getMerchantId().isEmpty()){
            paymentDTO.setPaymentUrl(paymentRequestDTO.getErrorUrl());
            payment.setPaymentUrl(paymentRequestDTO.getErrorUrl());
        } else{
            paymentDTO.setPaymentUrl(paymentRequestDTO.getSuccessUrl());
            payment.setPaymentUrl(paymentRequestDTO.getSuccessUrl());
        }
        paymentService.save(payment);

        paymentDTO.setPaymentId(Long.valueOf(paymentService.findAll().size()));


        return paymentDTO;
    }
    
}
