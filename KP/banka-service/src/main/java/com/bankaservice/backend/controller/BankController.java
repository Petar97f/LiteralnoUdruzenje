package com.bankaservice.backend.controller;

import com.bankaservice.backend.client.KpClient;
import com.bankaservice.backend.client.LuClient;
import com.bankaservice.backend.client.PccClient;
import com.bankaservice.backend.dto.*;
import com.bankaservice.backend.model.*;
import com.bankaservice.backend.service.BankService;
import com.bankaservice.backend.service.CardService;


import com.bankaservice.backend.service.PaymentService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;

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

    @Autowired
    private PccClient pccClient;

    @Autowired
    private KpClient kpClient;
    
    
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

            Log log = new Log(LogType.ERROR, payment.getPaymentUrl(), 1, "Bad request send");
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            log.setTimestamp(calendar.getTime());
            System.out.println(log);
        } else{
        	payment.setAmount(paymentRequestDTO.getAmount());
            paymentDTO.setPaymentUrl(paymentRequestDTO.getSuccessUrl());
            payment.setPaymentUrl(paymentRequestDTO.getSuccessUrl());

            Log log = new Log(LogType.INFO, payment.getPaymentUrl(), 1, "Success request");
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            log.setTimestamp(calendar.getTime());
            System.out.println(log);
        }
        payment.setMerchantId(paymentRequestDTO.getMerchantId());
        
        paymentService.save(payment);

        paymentDTO.setPaymentId(Long.valueOf(paymentService.findAll().size()));


        return paymentDTO;
    }
    @PostMapping(value = "/check")
    public String BankCheck(@RequestBody SecurityCheckDTO securityCheckDTO){
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
    	    if(cardBuyer.getAvailableMoney() - payment.getAmount() >= 0){
    		cardBuyer.setAvailableMoney(cardBuyer.getAvailableMoney() - payment.getAmount());
            Log log = new Log(LogType.INFO, card.getBank().getName(), 1, "Same bank, buy success");
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            log.setTimestamp(calendar.getTime());
            System.out.println(log);
            return null;
    	    }
            Log log1 = new Log(LogType.ERROR, cardBuyer.getCardNumber(), 1, "Not enough money on card");
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            log1.setTimestamp(calendar.getTime());
            System.out.println(log1);
    		return null;
    		
    	}
    	else {
            CardDTO cardDTO= new CardDTO(cardBuyer.getId(),cardBuyer.getClientId(),cardBuyer.getCardNumber(),cardBuyer.getExpirationDate(),cardBuyer.getCvc(),cardBuyer.getAvailableMoney(),cardBuyer.getPan(),cardBuyer.getSecurityCode(),cardBuyer.getMerchantId(),
                    cardBuyer.getMerchantPassword(),cardBuyer.getBank().getId());
    	    PccRequestDTO response1=pccClient.SendPccRequest(new PccRequestDTO(payment.getId(),new Date(),cardDTO));
            if(response1.getCardDTO().getAvailableMoney()-payment.getAmount()>=0){
                response1.getCardDTO().setAvailableMoney(response1.getCardDTO().getAvailableMoney()-payment.getAmount());
                Card crd=cardService.findCardById(response1.getCardDTO().getId());
                crd.setAvailableMoney(response1.getCardDTO().getAvailableMoney());
                cardService.save(crd);
                IssuerDTO issuerDTO = kpClient.getIsserData(payment.getMerchantId());
               PccRequest2DTO pccRequest2DTO=new PccRequest2DTO(response1.getAcquierOrderId(),response1.getAcquierTimestamp(),issuerDTO.getIssuerOrderId(),issuerDTO.getIssuerTimestamp(),true);
               PccRequest2DTO response2=pccClient.SendPccRequest2(pccRequest2DTO);
               TransactionDTO transactionDTO=new TransactionDTO(true,response2.getAcquierOrderId(),response2.getAcquierTimestamp(),response2.getIssuerOrderId(),payment.getId(),payment.getPaymentUrl());
               String returnString=kpClient.Transaction(transactionDTO);
               return returnString;
            }

    		return null;
    	}
    	
    	
    	
    }
 
    
    
    
}
