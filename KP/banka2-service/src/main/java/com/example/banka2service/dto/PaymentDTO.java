package com.example.banka2service.dto;

public class PaymentDTO {
    private String paymentUrl;
    private Long paymentId;
    private Boolean success;

    public PaymentDTO() {
    }

    public PaymentDTO(String paymentUrl, Long paymentId, Boolean success) {
        this.paymentUrl = paymentUrl;
        this.paymentId = paymentId;
        this.success = success;
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

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
