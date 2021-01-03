package com.bankaservice.backend.service;

import com.bankaservice.backend.model.Bank;
import com.bankaservice.backend.repository.BankRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankService {

    @Autowired
    BankRepository bankRepository;

    public List<Bank>findAll(){
    	return bankRepository.findAll();
    }
}
