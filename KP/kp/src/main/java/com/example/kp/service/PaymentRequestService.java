package com.example.kp.service;


import com.example.kp.model.Merchant;
import com.example.kp.model.PaymentRequest;
import com.example.kp.repository.PaymentRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentRequestService {

    @Autowired
    private PaymentRequestRepository paymentRequestRepository;

    public PaymentRequest findPaymentRequestById(Long id){
        return paymentRequestRepository.findPaymentRequestById(id);
    }
    public PaymentRequest save(PaymentRequest paymentRequest){
        return paymentRequestRepository.save(paymentRequest);
    }
    public List<PaymentRequest> findall()
    {
        return paymentRequestRepository.findAll();
    }
    public List<PaymentRequest> findAllByMerchant(Merchant merchant){return paymentRequestRepository.findPaymentRequestsByMerchant(merchant);}
}
