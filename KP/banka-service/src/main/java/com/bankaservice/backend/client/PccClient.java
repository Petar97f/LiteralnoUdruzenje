package com.bankaservice.backend.client;

import com.bankaservice.backend.dto.PccRequest2DTO;
import com.bankaservice.backend.dto.PccRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="pcc")
public interface PccClient {
    @PostMapping(value = "/PccRequest")
    public PccRequestDTO SendPccRequest(@RequestBody PccRequestDTO pccRequestDTO);
    @PostMapping(value = "/PccRequest2")
    public PccRequest2DTO SendPccRequest2(@RequestBody PccRequest2DTO pccRequest2DTO);
}
