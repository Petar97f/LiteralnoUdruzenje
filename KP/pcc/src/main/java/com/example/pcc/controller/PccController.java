package com.example.pcc.controller;

import com.example.pcc.client.Bank2Client;
import com.example.pcc.client.BankClient;
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
@CrossOrigin("*")
public class PccController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private BankClient bankClient;

    @Autowired
    private Bank2Client bank2Client;

    @PostMapping(value = "/PccRequest")
    public PccRequest2DTO SendPccRequest(@RequestBody PccRequestDTO pccRequestDTO){

        Request request = new Request();
        request.setCardId(pccRequestDTO.getCardDTO().getId());
        request.setAcquierOrderId(pccRequestDTO.getAcquierOrderId());
        request.setAcquierTimestamp(pccRequestDTO.getAcquierTimestamp());
        request.setAmount(pccRequestDTO.getAmount());
        requestService.save(request);
        System.out.println(pccRequestDTO.getCardDTO().getPan());
        Long bankId;
        Long banka1=bankClient.getBankId(pccRequestDTO.getCardDTO().getPan());
        if(banka1==1L)
            bankId=1L;
        else bankId=2L;

        PccRequest2DTO pccRequest2DTO;
        if(bankId==1L){
           pccRequest2DTO=bankClient.ClientBank(pccRequestDTO);
        }else pccRequest2DTO=bank2Client.ClientBank(pccRequestDTO);


        return pccRequest2DTO;
    }

    @PostMapping(value = "/PccRequest2")
    public PccRequest2DTO SendPccRequest2(@RequestBody PccRequest2DTO pccRequest2DTO){
        return pccRequest2DTO;
    }
}
