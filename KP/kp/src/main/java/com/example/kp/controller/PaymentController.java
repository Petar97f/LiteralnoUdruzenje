package com.example.kp.controller;



import com.example.kp.client.Bank2Client;
import com.example.kp.client.BankClient;
import com.example.kp.client.PaypalClient;
import com.example.kp.dto.*;
import com.example.kp.model.Log;
import com.example.kp.model.LogType;
import com.example.kp.model.Merchant;
import com.example.kp.model.PaymentRequest;
import com.example.kp.model.PaymentType;
import com.example.kp.repository.MerchantRepository;
import com.example.kp.service.LoggingService;
import com.example.kp.service.MerchantService;
import com.example.kp.service.PaymentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin("*")
@RestController
public class PaymentController {

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private PaymentRequestService paymentRequestService;

    @Autowired
    private BankClient bankClient;
    @Autowired 
    private PaypalClient paypalClient;

    @Autowired
    private Bank2Client bank2Client;
    
    @Autowired
    private LoggingService loggingService;

	@GetMapping(value = "/getTypes/{merchantId}")
    public ResponseEntity<?> getUser(@PathVariable("merchantId") String merchantId) {
    	loggingService.writeLog("INFO","| getUser is called", getClass().getSimpleName());
        Merchant merchant = merchantService.findMerchantByMerchantId(merchantId);
        if (merchant == null) {
        	loggingService.writeLog("SEVERE","| Request denied", getClass().getSimpleName());
    		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        PaymentTypesDTO paymentTypesDTO = new PaymentTypesDTO();
        paymentTypesDTO.setPaymentTypes(merchant.getPaymentTypes());
    	return new ResponseEntity<>(paymentTypesDTO, HttpStatus.OK);
    }


    @GetMapping(value = "/getMerchantData/{merchantId}")
    public ResponseEntity<?> getIsserData(@PathVariable("merchantId") String merchantId) {
    	loggingService.writeLog("INFO","| getIsserData is called",  getClass().getSimpleName());
    	if (merchantId == null) {
    		loggingService.writeLog("SEVERE","| Request denied",  getClass().getSimpleName());
    		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    	}
        List<PaymentRequest> reqs=paymentRequestService.findAllByMerchant(merchantService.findMerchantByMerchantId(merchantId));
        IssuerDTO issuerDTO= new IssuerDTO(Long.valueOf(reqs.size()),new Date());
        return new ResponseEntity<>(issuerDTO, HttpStatus.OK);
    }

    @PostMapping(value="/addMerchant", produces="application/json")
    public ResponseEntity<?> addMerchant(@RequestBody MerchantDTO merchantDTO) {
    	loggingService.writeLog("INFO","| addMerchant is called",  getClass().getSimpleName());
    	HashMap<String, String> message = new HashMap<String, String>();
    	if (merchantDTO == null) {
    		loggingService.writeLog("SEVERE","| Request denied",  getClass().getSimpleName());
    		message.put("message", "Please fill out all fields");
    		message.put("status", "fail");
    		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    	}
    	List<PaymentType> paymentTypes = new ArrayList<>();
    	System.out.println(merchantDTO);
    	List<String> typesList = Arrays.asList(merchantDTO.getPaymentTypes().split(","));
    	if (!typesList.isEmpty()) {
    		for (String type : typesList ) {
    			if (type.equals("bank")) {
    				paymentTypes.add(PaymentType.BANK);
    			} else if (type.equals("bitcoin")) {
    				paymentTypes.add(PaymentType.CRYPTO);
    			} else if (type.equals("paypal")) {
    				paymentTypes.add(PaymentType.PAYPAL);
    			}
    		}
    	}
    	Merchant m = new Merchant();
    	m.setAddress(merchantDTO.getAddress());
    	m.setName(merchantDTO.getName());
    	m.setPassword(merchantDTO.getPassword());
    	m.setPhoneNumber(merchantDTO.getPhoneNumber());
    	m.setPaymentTypes(paymentTypes);
    	m.setBankId(merchantDTO.getBankId());
    	m.setErrorUrl("http://localhost:3005/error");
    	m.setFailedUrl("http://localhost:3005/failed");
    	m.setSuccessUrl("http://localhost:3005/success");
    	merchantService.save(m);
    	loggingService.writeLog("INFO","| new merchant added",  getClass().getSimpleName());
    	message.put("message", "Account created");
		message.put("status", "success");
    	return new ResponseEntity<>(message, HttpStatus.OK);
    }
    
    
    @PostMapping(value="/PaymentBank", produces="application/json")
    public @ResponseBody ResponseDTO BankPay(@RequestBody RequestDTO requestDTO){
    	loggingService.writeLog("INFO","| BankPay is called",  getClass().getSimpleName());
        System.out.println(requestDTO.toString());
        System.out.println(requestDTO.getAmount());
        System.out.println(requestDTO.getId());
        PaymentRequest paymentRequest= new PaymentRequest();
        PaymentRequestDTO paymentRequestDTO=new PaymentRequestDTO();
        System.out.println(requestDTO.getAmount());
        System.out.println(requestDTO.getId());
        paymentRequest.setAmount(Double.valueOf(requestDTO.getAmount()));
        paymentRequestDTO.setAmount(Double.valueOf(requestDTO.getAmount()));

        Merchant merchant=merchantService.findMerchantByMerchantId(requestDTO.getId());
        paymentRequest.setMerchant(merchant);
        System.out.println(merchant.toString());
        paymentRequestDTO.setErrorUrl(merchant.getErrorUrl());
        paymentRequestDTO.setFailedUrl(merchant.getFailedUrl());
        paymentRequestDTO.setSuccessUrl(merchant.getSuccessUrl());
        paymentRequestDTO.setMerchantId(merchant.getMerchantId());
        paymentRequestDTO.setMerchantPassword(merchant.getPassword());
        paymentRequestDTO.setMerchantTimestamp(new Date());

        List<PaymentRequest> reqs =paymentRequestService.findAllByMerchant(merchant);
        if(reqs.isEmpty()){
            paymentRequestDTO.setMerchantOrderId(1L);
        } else{
            Long max=1L;
            for (PaymentRequest r:reqs
                 ) {
                if(max<=r.getMerchantOrderId())
                    max=r.getMerchantOrderId();
            }
            paymentRequestDTO.setMerchantOrderId(max++);
        }
        paymentRequest.setMerchantOrderId(paymentRequestDTO.getMerchantOrderId());
        paymentRequest.setMerchantTimestamp(new Date());

        loggingService.writeLog("INFO","| Send request to kp",  getClass().getSimpleName());
        Log log = new Log(LogType.INFO, requestDTO.getId(), 1, "Send request to kp");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        log.setTimestamp(calendar.getTime());
        System.out.println(log);


        paymentRequestService.save(paymentRequest);

        PaymentDTO paymentDTO;

        String link;
        if(merchant.getBankId() == 1){
            paymentDTO=bankClient.BankPay(paymentRequestDTO);
            link = "http://localhost:4200/?paymentId="+paymentDTO.getPaymentId().toString();}
        else {paymentDTO= bank2Client.Bank2Pay(paymentRequestDTO);
            link = "http://localhost:4201/?paymentId="+paymentDTO.getPaymentId().toString();
        }
        if(paymentDTO.getSuccess())
            return new ResponseDTO("success",link);
        else return new ResponseDTO("fail",link);
    }

    @PostMapping(value = "/TrasactionDone")
    public String Transaction(@RequestBody TransactionDTO transactionDTO){
        return transactionDTO.getPaymentUrl();
    }
    
    @PostMapping(value="/PaymentPaypal")
    public Map<String, Object> PaypalPay( @RequestBody RequestDTO requestDTO) {
    	return paypalClient.makePayment(requestDTO.getAmount().toString());
    }
    
    @PostMapping(value="/CriptoPayment")
    public Map<String, Object> CriptoPayment( @RequestBody CryptoAtributeDTO cryptoDTO) {
    	Map<String, Object> response = new HashMap<String, Object>();
    	String redirectUrl = "https://api-sandbox.coingate.com/v2/orders";
    	redirectUrl.concat("?price_amount="+cryptoDTO.getAmount());
    	redirectUrl.concat("&price_amount="+cryptoDTO.getCurrency());
    	redirectUrl.concat("&price_amount="+cryptoDTO.getRecieveCurrency());
    	response.put("status", "success");
        response.put("redirect_url", redirectUrl);
    	return response;
    }
    
    
    
    
}
