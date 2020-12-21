package com.bankaservice.backend.service;

import com.bankaservice.backend.model.Payment;
import com.bankaservice.backend.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> findAll(){return paymentRepository.findAll();}
    public Payment findPaymentById(Long id){return paymentRepository.findPaymentById(id);}
    public void save(Payment payment){paymentRepository.save(payment);}

}
