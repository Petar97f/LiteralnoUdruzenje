package com.example.kp.controller;



import com.example.kp.client.Bank2Client;
import com.example.kp.client.BankClient;
import com.example.kp.client.PaypalClient;
import com.example.kp.dto.*;
import com.example.kp.model.Log;
import com.example.kp.model.LogType;
import com.example.kp.model.Merchant;
import com.example.kp.model.PaymentRequest;
import com.example.kp.repository.MerchantRepository;
import com.example.kp.service.MerchantService;
import com.example.kp.service.PaymentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Date;
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


    @GetMapping(value = "/getTypes/{merchantId}")
    public PaymentTypesDTO getUser(@PathVariable("merchantId") String merchantId) {
        Merchant merchant = merchantService.findMerchantByMerchantId(merchantId);
        PaymentTypesDTO paymentTypesDTO = new PaymentTypesDTO();
        paymentTypesDTO.setPaymentTypes(merchant.getPaymentTypes());
        return paymentTypesDTO;
    }


    @GetMapping(value = "/getMerchantData/{merchantId}")
    public IssuerDTO getIsserData(@PathVariable("merchantId") String merchantId){
        List<PaymentRequest> reqs=paymentRequestService.findAllByMerchant(merchantService.findMerchantByMerchantId(merchantId));
        IssuerDTO issuerDTO= new IssuerDTO(Long.valueOf(reqs.size()),new Date());
        return issuerDTO;
    }

    @PostMapping(value="/PaymentBank", produces="application/json")
    public @ResponseBody ResponseDTO BankPay(@RequestBody RequestDTO requestDTO){
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
    
    
}
