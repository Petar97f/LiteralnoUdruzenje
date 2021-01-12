package com.example.banka2service.client;

import com.example.banka2service.dto.IssuerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="banka")
public interface BankClient {
    @GetMapping(value = "/getCardData/{pan}")
    public Long getCardId(@PathVariable("pan") String pan);
}
