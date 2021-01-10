package com.example.banka2service.service;


import com.example.banka2service.model.Bank;
import com.example.banka2service.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {

    @Autowired
    BankRepository bankRepository;

    public List<Bank>findAll(){
    	return bankRepository.findAll();
    }
}
