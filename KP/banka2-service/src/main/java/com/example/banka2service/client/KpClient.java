package com.example.banka2service.client;

import com.example.banka2service.dto.IssuerDTO;
import com.example.banka2service.dto.TransactionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="kp")
public interface KpClient {
    @GetMapping(value = "/getMerchantData/{merchantId}")
    public IssuerDTO getIsserData(@PathVariable("merchantId") String merchantId);
    @PostMapping(value = "/TrasactionDone")
    public String Transaction(@RequestBody TransactionDTO transactionDTO);
}
