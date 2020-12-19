package com.example.kp.dto;

public class PaymentDTO {
    private String paymentUrl;
    private String paymentId;

    public PaymentDTO() {
    }

    public PaymentDTO(String paymentUrl, String paymentId) {
        this.paymentUrl = paymentUrl;
        this.paymentId = paymentId;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public void setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
}
