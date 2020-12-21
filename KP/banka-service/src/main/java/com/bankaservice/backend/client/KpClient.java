package com.bankaservice.backend.client;

import com.bankaservice.backend.dto.IssuerDTO;
import com.bankaservice.backend.dto.PccRequestDTO;
import com.bankaservice.backend.dto.TransactionDTO;
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
