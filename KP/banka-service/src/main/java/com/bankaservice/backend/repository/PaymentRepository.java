package com.bankaservice.backend.repository;

import com.bankaservice.backend.model.Card;
import com.bankaservice.backend.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findAll();
    Payment findPaymentById(Long id);
}

