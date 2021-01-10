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

	@GetMapping(value="/getBankId")
    public Long getBankId(@RequestBody String pan){
        if(cardService.findByPan(pan)!=null)
            return 1L;
        else return 0L;
    }

    @PostMapping(value = "/BankPay")
    public PaymentDTO BankPay(@RequestBody PaymentRequestDTO paymentRequestDTO){
        PaymentDTO paymentDTO=new PaymentDTO();
        Payment payment=new Payment();
        if(paymentRequestDTO.getAmount() == null || paymentRequestDTO.getErrorUrl().isEmpty() || paymentRequestDTO.getFailedUrl().isEmpty() || paymentRequestDTO.getMerchantId().isEmpty()
        || paymentRequestDTO.getSuccessUrl().isEmpty() || paymentRequestDTO.getMerchantOrderId() == null || paymentRequestDTO.getMerchantTimestamp() == null || paymentRequestDTO.getMerchantId().isEmpty()){
            paymentDTO.setPaymentUrl(paymentRequestDTO.getErrorUrl());
            paymentDTO.setSuccess(false);
            payment.setPaymentUrl(paymentRequestDTO.getErrorUrl());
            payment.setSuccess(false);

            Log log = new Log(LogType.ERROR, payment.getPaymentUrl(), 1, "Bad request send");
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            log.setTimestamp(calendar.getTime());
            System.out.println(log);
        } else{
        	payment.setAmount(paymentRequestDTO.getAmount());
            paymentDTO.setPaymentUrl(paymentRequestDTO.getSuccessUrl());
            paymentDTO.setSuccess(true);
            payment.setPaymentUrl(paymentRequestDTO.getSuccessUrl());
            payment.setSuccess(true);

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

        if(card.getBank() == cardBuyer.getBank()) {
            if(cardBuyer.getAvailableMoney() - payment.getAmount() >= 0){
                cardBuyer.setAvailableMoney(cardBuyer.getAvailableMoney() - payment.getAmount());
                card.setAvailableMoney(card.getAvailableMoney()+payment.getAmount());
                cardService.save(cardBuyer);
                cardService.save(card);
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
            CardDTO cardDTO= new CardDTO(securityCheckDTO.getPan(),securityCheckDTO.getSecurityCode(),securityCheckDTO.getCardHolderName(),securityCheckDTO.getExpirationDate());
            PccRequest2DTO response=pccClient.SendPccRequest(new PccRequestDTO(payment.getId(),new Date(),cardDTO,payment.getAmount()));

            if(response==null)
                return null;
            else{
                card.setAvailableMoney(card.getAvailableMoney()+ payment.getAmount());
                cardService.save(card);
                TransactionDTO transactionDTO=new TransactionDTO(true,response.getAcquierOrderId(),response.getAcquierTimestamp(),response.getIssuerOrderId(),payment.getId(),payment.getPaymentUrl());
                String returnString=kpClient.Transaction(transactionDTO);
                return returnString;
            }
        }
    }



    @PostMapping(value = "/ClientBank")
    public PccRequest2DTO ClientBank(@RequestBody PccRequestDTO pccRequestDTO){
        Card card=cardService.findByPan(pccRequestDTO.getCardDTO().getPan());
        if(card.getAvailableMoney()-pccRequestDTO.getAmount()>=0){
            card.setAvailableMoney(card.getAvailableMoney()-pccRequestDTO.getAmount());
            cardService.save(card);
            IssuerDTO issuerDTO = kpClient.getIsserData(card.getMerchantId());
            PccRequest2DTO pccRequest2DTO=new PccRequest2DTO(pccRequestDTO.getAcquierOrderId(),pccRequestDTO.getAcquierTimestamp(),issuerDTO.getIssuerOrderId(),issuerDTO.getIssuerTimestamp(),true);
            return pccRequest2DTO;
        }
        return null;
    }



}
