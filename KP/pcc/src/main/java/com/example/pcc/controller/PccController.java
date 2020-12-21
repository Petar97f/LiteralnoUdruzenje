package com.example.pcc.controller;

import com.example.pcc.dto.PccRequest2DTO;
import com.example.pcc.dto.PccRequestDTO;
import com.example.pcc.model.Request;
import com.example.pcc.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class PccController {

    @Autowired
    private RequestService requestService;

    @PostMapping(value = "/PccRequest")
    public PccRequestDTO SendPccRequest(@RequestBody PccRequestDTO pccRequestDTO){

        Request request = new Request();
        request.setCardId(pccRequestDTO.getCardDTO().getId());
        request.setAcquierOrderId(pccRequestDTO.getAcquierOrderId());
        request.setAcquierTimestamp(pccRequestDTO.getAcquierTimestamp());
        requestService.save(request);
        return pccRequestDTO;
    }

    @PostMapping(value = "/PccRequest2")
    public PccRequest2DTO SendPccRequest2(@RequestBody PccRequest2DTO pccRequest2DTO){
        return pccRequest2DTO;
    }
}
