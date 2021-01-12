package com.example.banka2service.client;


import com.example.banka2service.dto.PccRequest2DTO;
import com.example.banka2service.dto.PccRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="pcc")
public interface PccClient {
    @PostMapping(value = "/PccRequest")
    public PccRequest2DTO SendPccRequest(@RequestBody PccRequestDTO pccRequestDTO);
}
