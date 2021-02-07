package com.bankaservice.backend.controller;

import com.bankaservice.backend.client.BankClient;
import com.bankaservice.backend.client.KpClient;
import com.bankaservice.backend.client.LuClient;
import com.bankaservice.backend.client.PccClient;
import com.bankaservice.backend.dto.*;
import com.bankaservice.backend.model.*;
import com.bankaservice.backend.service.CardService;


import com.bankaservice.backend.service.LoggingService;
import com.bankaservice.backend.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;



@RestController
@CrossOrigin("*")
public class BankController {
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

    @Autowired
    private BankClient bankClient;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LoggingService loggingService;


    @GetMapping(value = "/getCardOwner")
    public UserDTO getUser(@RequestBody Card card) {

        return luClient.getUser(card.getClientId());


    }

    @GetMapping(value = "/getCardData/{pan}")
    public Long getCardId(@PathVariable("pan") String pan){
        if(cardService.findByPan(pan)!=null){
            loggingService.writeLog("INFO","| Dosao",getClass().getSimpleName());
            return cardService.findByPan(pan).getBankId();
        }else return null;
    }

    @PostMapping(value="/getBankId")
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
            loggingService.writeLog("SEVERE","| Bad request send",getClass().getSimpleName());
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

            loggingService.writeLog("INFO","| Success request",getClass().getSimpleName());
            Log log = new Log(LogType.INFO, payment.getPaymentUrl(), 1, "Success request");
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            log.setTimestamp(calendar.getTime());
            System.out.println(log);
        }
        payment.setMerchantId(paymentRequestDTO.getMerchantId());
        loggingService.writeLog("INFO","| New payment saved",getClass().getSimpleName());
        paymentService.save(payment);

        paymentDTO.setPaymentId(Long.valueOf(paymentService.findAll().size()));


        return paymentDTO;
    }
    @PostMapping(value = "/check")
    public ResponseEntity<ResponseDTO> BankCheck(@RequestBody SecurityCheckDTO securityCheckDTO){
        Payment payment = paymentService.findPaymentById(securityCheckDTO.getPaymentId());
        System.out.println("Payment: "+payment);
        System.out.println(securityCheckDTO.toString());
        Long clientId;
        Long bankId;
        Card cardBuyer;
        if(cardService.findByPan(securityCheckDTO.getPan())!=null) {
            bankId =cardService.findByPan(securityCheckDTO.getPan()).getBankId();
            cardBuyer = cardService.findByPan(securityCheckDTO.getPan());
            System.out.println(bankId);
            System.out.println(cardBuyer.toString());
           System.out.println("code "+ passwordEncoder.matches(securityCheckDTO.getSecurityCode(),cardBuyer.getSecurityCode()));
            if(passwordEncoder.matches(securityCheckDTO.getPan(),cardBuyer.getPan()) && cardBuyer.getExpirationDate().equals(securityCheckDTO.getExpirationDate()) && passwordEncoder.matches(securityCheckDTO.getSecurityCode(),cardBuyer.getSecurityCode())){
                System.out.println("usao unutra");
            }else{
                System.out.println("usao u else");
                loggingService.writeLog("SEVERE","| Wrong input data",getClass().getSimpleName());
                return new ResponseEntity<ResponseDTO>(new ResponseDTO("fail","http://localhost:3005/failed"),HttpStatus.BAD_REQUEST);
            }
        } else {
            System.out.println("usao 88");
            bankId = bankClient.getCardId(securityCheckDTO.getPan());
            if(bankId == null){
                System.out.println("usao u null posle 88");
                loggingService.writeLog("SEVERE","| Card does not exist",getClass().getSimpleName());
                return new ResponseEntity<ResponseDTO>(new ResponseDTO("fail","http://localhost:3005/error"),HttpStatus.BAD_REQUEST);
            }
            cardBuyer = new Card();
        }

        //Long sellerId = securityCheckDTO.getPaymentId();
        Card card = cardService.findByMerchantId(payment.getMerchantId());
        Long sellerId = card.getClientId();
        System.out.println("SellerId: "+sellerId);

        if(card.getBankId() == bankId) {
            if(cardBuyer.getAvailableMoney() - payment.getAmount() >= 0){
                cardBuyer.setAvailableMoney(cardBuyer.getAvailableMoney() - payment.getAmount());
                card.setAvailableMoney(card.getAvailableMoney()+payment.getAmount());
                cardService.save(cardBuyer);
                cardService.save(card);
                Log log = new Log(LogType.INFO, card.getBankId().toString(), 1, "Same bank, buy success");
                Date date = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                log.setTimestamp(calendar.getTime());
                System.out.println(log);
                loggingService.writeLog("INFO","| Transaction successful",getClass().getSimpleName());
                return new ResponseEntity<ResponseDTO>(new ResponseDTO("success","http://localhost:3005/success"), HttpStatus.OK);
            }
            Log log1 = new Log(LogType.ERROR, cardBuyer.getCardNumber(), 1, "Not enough money on card");
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            log1.setTimestamp(calendar.getTime());
            System.out.println(log1);
            loggingService.writeLog("SEVERE","| Transaction failed",getClass().getSimpleName());
            return new ResponseEntity<ResponseDTO>(new ResponseDTO("fail","http://localhost:3005/failed"),HttpStatus.BAD_REQUEST);

        }
        else {
            CardDTO cardDTO= new CardDTO(securityCheckDTO.getPan(),securityCheckDTO.getSecurityCode(),securityCheckDTO.getCardHolderName(),securityCheckDTO.getExpirationDate());
            PccRequest2DTO response=pccClient.SendPccRequest(new PccRequestDTO(payment.getId(),new Date(),cardDTO,payment.getAmount()));
            System.out.println("usao ovde na mesto 8");
            if(response==null) {
                System.out.println("usao na 9");
                loggingService.writeLog("SEVERE","| Transaction failed",getClass().getSimpleName());
                return new ResponseEntity<ResponseDTO>(new ResponseDTO("fail", "http://localhost:3005/error"), HttpStatus.BAD_REQUEST);

            }else{
                System.out.println("usao na 10");
                card.setAvailableMoney(card.getAvailableMoney()+ payment.getAmount());
                cardService.save(card);
                TransactionDTO transactionDTO=new TransactionDTO(true,response.getAcquierOrderId(),response.getAcquierTimestamp(),response.getIssuerOrderId(),payment.getId(),payment.getPaymentUrl());
                String returnString=kpClient.Transaction(transactionDTO);
                loggingService.writeLog("INFO","| Transaction successful",getClass().getSimpleName());
                return new ResponseEntity<ResponseDTO>(new ResponseDTO("success","http://localhost:3005/success"),HttpStatus.OK);
            }
        }
    }



    @PostMapping(value = "/ClientBank")
    public PccRequest2DTO ClientBank(@RequestBody PccRequestDTO pccRequestDTO){
        Card card=cardService.findByPan(pccRequestDTO.getCardDTO().getPan());
        if(passwordEncoder.matches(pccRequestDTO.getCardDTO().getSecurityCode(),card.getSecurityCode())  && card.getExpirationDate().equals(pccRequestDTO.getCardDTO().getExpirationDate())){
                System.out.println("usao u clienta");
        }else return null;
        if(card.getAvailableMoney()-pccRequestDTO.getAmount()>=0){
            card.setAvailableMoney(card.getAvailableMoney()-pccRequestDTO.getAmount());
            cardService.save(card);
            IssuerDTO issuerDTO = kpClient.getIsserData(card.getMerchantId());
            loggingService.writeLog("INFO","| Transaction successful",getClass().getSimpleName());
            PccRequest2DTO pccRequest2DTO=new PccRequest2DTO(pccRequestDTO.getAcquierOrderId(),pccRequestDTO.getAcquierTimestamp(),issuerDTO.getIssuerOrderId(),issuerDTO.getIssuerTimestamp(),true);
            return pccRequest2DTO;
        }
        return null;
    }

    @PostMapping(value="/addCard")
    public BankResponseDTO addCard(@RequestParam("lastMerchantId") String lastMerchantId){
        Card c=new Card();
        BankResponseDTO bankResponseDTO=new BankResponseDTO();
        Long l=Long.parseLong(lastMerchantId)+1;
        c.setMerchantId(String.valueOf(l));
        c.setMerchantPassword("1234");
        c.setSecurityCode("1234");
        c.setPan(passwordEncoder.encode(String.valueOf(l)));
        c.setAvailableMoney(0F);
        c.setBankId(1L);
        c.setCardNumber(c.getPan());
        c.setCvc("123");
        LocalDate localDate = LocalDate.now();
        localDate.plusYears(2);
        c.setExpirationDate(String.valueOf(localDate.getMonthValue())+"/"+String.valueOf(localDate.getYear()));
        System.out.println(c);
        cardService.saveFirst(c);
        bankResponseDTO.setMerchantId(c.getMerchantId());
        bankResponseDTO.setMerchantPassword(c.getMerchantPassword());
        loggingService.writeLog("INFO","| New card added",getClass().getSimpleName());
        return bankResponseDTO;
    }

}
