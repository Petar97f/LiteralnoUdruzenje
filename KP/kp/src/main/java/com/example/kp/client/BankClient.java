package com.example.kp.client;

import com.example.kp.dto.BankResponseDTO;
import com.example.kp.dto.MerchantDTO;
import com.example.kp.dto.PaymentDTO;
import com.example.kp.dto.PaymentRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "banka")
public interface BankClient {

    @PostMapping(value = "/BankPay")
    public PaymentDTO BankPay(@RequestBody PaymentRequestDTO paymentRequestDTO);

    @PostMapping(value="/addCard")
    public BankResponseDTO addCard(@RequestParam("lastMerchantId") String lastMerchantId);
}
