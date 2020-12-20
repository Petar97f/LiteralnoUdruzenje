package com.bankaservice.backend.controller;

import com.bankaservice.backend.client.LuClient;
import com.bankaservice.backend.dto.PaymentDTO;
import com.bankaservice.backend.dto.PaymentRequestDTO;
import com.bankaservice.backend.dto.PccRequest;
import com.bankaservice.backend.dto.SecurityCheckDTO;
import com.bankaservice.backend.dto.UserDTO;
import com.bankaservice.backend.model.Bank;
import com.bankaservice.backend.model.Card;
import com.bankaservice.backend.model.Payment;
import com.bankaservice.backend.service.BankService;
import com.bankaservice.backend.service.CardService;


import com.bankaservice.backend.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
        	payment.setAmount(paymentRequestDTO.getAmount());
            paymentDTO.setPaymentUrl(paymentRequestDTO.getSuccessUrl());
            payment.setPaymentUrl(paymentRequestDTO.getSuccessUrl());
        }
        payment.setMerchantId(paymentRequestDTO.getMerchantId());
        
        paymentService.save(payment);

        paymentDTO.setPaymentId(Long.valueOf(paymentService.findAll().size()));


        return paymentDTO;
    }
    @PostMapping(value = "/check")
    public PccRequest BankCheck(@RequestBody SecurityCheckDTO securityCheckDTO){
    	Payment payment = paymentService.findPaymentById(securityCheckDTO.getPaymentId());
    	System.out.println("Payment: "+payment);
    	Long clientId = luClient.getUser(cardService.findByPan(securityCheckDTO.getPan()).getClientId()).getId();
    	System.out.println("CliendId: " +clientId);
    	//Long sellerId = securityCheckDTO.getPaymentId();
    	Card card = cardService.findByMerchantId(payment.getMerchantId());
    	Long sellerId = card.getClientId();
    	System.out.println("SellerId: "+sellerId);
    	Long bankId = 0L;
    	Long bankId2 = 0L;
    	Card cardBuyer = cardService.findByPan(securityCheckDTO.getPan());

    	//Card cardBuyer = cardService.findByPan(securityCheckDTO.getPan());
    /*	for(Bank b: bankService.findAll()) {
    		if(b.getClientCards().contains(clientId)) {
    			bankId = b.getId();
    		}
    		if(b.getClients().contains(sellerId)) {
    			bankId2 = b.getId();
    		}
    	}
		System.out.println("BankId1: "+bankId);
		System.out.println("BankId2: "+bankId2);
*/
    	if(card.getBank() == cardBuyer.getBank()) {
    		cardBuyer.setAvailableMoney(cardBuyer.getAvailableMoney() - payment.getAmount());
    		return null;
    		
    	}
    	else {
    		return new PccRequest(payment.getId(), new Date(),cardBuyer.getId() );
    	}
    	
    	
    	
    }
 
    
    
    
}
