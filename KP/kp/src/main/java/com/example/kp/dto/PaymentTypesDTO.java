package com.example.kp.dto;

import com.example.kp.model.PaymentType;

import java.util.List;

public class PaymentTypesDTO {
    private List<PaymentType> paymentTypes;

    public PaymentTypesDTO() {
    }

    public List<PaymentType> getPaymentTypes() {
        return paymentTypes;
    }

    public void setPaymentTypes(List<PaymentType> paymentTypes) {
        this.paymentTypes = paymentTypes;
    }
}
