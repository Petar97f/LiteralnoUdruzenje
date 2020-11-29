package com.bankaservice.backend.controller;

import com.bankaservice.backend.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
public class BankController {
    @Autowired
    BankService bankService;
}
