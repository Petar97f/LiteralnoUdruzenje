package com.bankaservice.backend.dto;

public class PaymentDTO {
    private String paymentUrl;
    private Long paymentId;

    public PaymentDTO() {
    }

    public PaymentDTO(String paymentUrl, Long paymentId) {
        this.paymentUrl = paymentUrl;
        this.paymentId = paymentId;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public void setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }
}
