package com.example.banka2service.repository;

import com.example.banka2service.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findAll();
    Payment findPaymentById(Long id);
}

