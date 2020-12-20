package com.example.kp.repository;

import com.example.kp.model.Merchant;
import com.example.kp.model.PaymentRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRequestRepository extends JpaRepository<PaymentRequest,Long> {

    PaymentRequest findPaymentRequestById(Long id);

    List<PaymentRequest> findAll();
    List<PaymentRequest> findPaymentRequestsByMerchant(Merchant merchant);
}