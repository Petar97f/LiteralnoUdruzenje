package com.bankaservice.backend.dto;

import java.util.Date;

public class TransactionDTO {
    private boolean result;
    private Long acquierOrderId;
    private Date acquierTimestamp;
    private Long merchantOrderId;
    private Long paymentId;
    private String paymentUrl;

    public TransactionDTO() {
    }

    public TransactionDTO(boolean result, Long acquierOrderId, Date acquierTimestamp, Long merchantOrderId, Long paymentId, String paymentUrl) {
        this.result = result;
        this.acquierOrderId = acquierOrderId;
        this.acquierTimestamp = acquierTimestamp;
        this.merchantOrderId = merchantOrderId;
        this.paymentId = paymentId;
        this.paymentUrl = paymentUrl;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Long getAcquierOrderId() {
        return acquierOrderId;
    }

    public void setAcquierOrderId(Long acquierOrderId) {
        this.acquierOrderId = acquierOrderId;
    }

    public Date getAcquierTimestamp() {
        return acquierTimestamp;
    }

    public void setAcquierTimestamp(Date acquierTimestamp) {
        this.acquierTimestamp = acquierTimestamp;
    }

    public Long getMerchantOrderId() {
        return merchantOrderId;
    }

    public void setMerchantOrderId(Long merchantOrderId) {
        this.merchantOrderId = merchantOrderId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public void setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
    }
}
