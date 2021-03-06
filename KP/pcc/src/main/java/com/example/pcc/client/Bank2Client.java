package com.example.pcc.client;

import com.example.pcc.dto.PccRequest2DTO;
import com.example.pcc.dto.PccRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="banka2")
public interface Bank2Client {
    @PostMapping(value = "/getBankId")
    public Long getBankId(@RequestBody String pan);
    @PostMapping(value = "/ClientBank")
    public PccRequest2DTO ClientBank(@RequestBody PccRequestDTO pccRequestDTO);
}
