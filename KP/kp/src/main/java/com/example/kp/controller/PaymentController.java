package com.example.kp.controller;



import com.example.kp.client.BankClient;
import com.example.kp.dto.*;
import com.example.kp.model.Log;
import com.example.kp.model.LogType;
import com.example.kp.model.Merchant;
import com.example.kp.model.PaymentRequest;
import com.example.kp.repository.MerchantRepository;
import com.example.kp.service.MerchantService;
import com.example.kp.service.PaymentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
@CrossOrigin("*")
@RequestMapping("")
@RestController
public class PaymentController {

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private PaymentRequestService paymentRequestService;

    @Autowired
    private BankClient bankClient;


    @GetMapping(value = "/getTypes/{merchantId}")
    public PaymentTypesDTO getUser(@PathVariable("merchantId") String merchantId) {
        Merchant merchant = merchantService.findMerchantById(merchantId);
        PaymentTypesDTO paymentTypesDTO = new PaymentTypesDTO();
        paymentTypesDTO.setPaymentTypes(merchant.getPaymentTypes());
        return paymentTypesDTO;
    }

    @GetMapping(value = "/getMerchantData/{merchantId}")
    public IssuerDTO getIsserData(@PathVariable("merchantId") String merchantId){
        List<PaymentRequest> reqs=paymentRequestService.findAllByMerchant(merchantService.findMerchantById(merchantId));
        IssuerDTO issuerDTO= new IssuerDTO(Long.valueOf(reqs.size()),new Date());
        return issuerDTO;
    }

    @PostMapping(value="/PaymentBank")
    public String BankPay( @RequestBody RequestDTO requestDTO){
        PaymentRequest paymentRequest= new PaymentRequest();
        PaymentRequestDTO paymentRequestDTO=new PaymentRequestDTO();

        paymentRequest.setAmount(requestDTO.getAmount());
        paymentRequestDTO.setAmount(requestDTO.getAmount());

        Merchant merchant=merchantService.findMerchantById(requestDTO.getId());
        paymentRequest.setMerchant(merchant);

        paymentRequestDTO.setErrorUrl(merchant.getErrorUrl());
        paymentRequestDTO.setFailedUrl(merchant.getFailedUrl());
        paymentRequestDTO.setSuccessUrl(merchant.getSuccessUrl());
        paymentRequestDTO.setMerchantId(merchant.getId());
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

        PaymentDTO paymentDTO=bankClient.BankPay(paymentRequestDTO);



        return paymentDTO.getPaymentUrl();
    }

    @PostMapping(value = "/TrasactionDone")
    public String Transaction(@RequestBody TransactionDTO transactionDTO){
        return transactionDTO.getPaymentUrl();
    }
}
