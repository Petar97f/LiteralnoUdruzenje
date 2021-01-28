package com.bankaservice.backend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="banka2")
public interface BankClient {
    @GetMapping(value = "/getCardData/{pan}")
    public Long getCardId(@PathVariable("pan") String pan);
}
