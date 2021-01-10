package com.example.kp.client;

import com.example.kp.dto.PaymentDTO;
import com.example.kp.dto.PaymentRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "banka2")
public interface Bank2Client {

    @PostMapping(value = "/BankPay")
    public PaymentDTO Bank2Pay(@RequestBody PaymentRequestDTO paymentRequestDTO);
}