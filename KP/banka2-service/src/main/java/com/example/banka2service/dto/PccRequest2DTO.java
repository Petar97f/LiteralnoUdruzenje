package com.example.banka2service.dto;

import java.util.Date;

public class PccRequest2DTO {
    private Long acquierOrderId;
    private Date acquierTimestamp;
    private Long issuerOrderId;
    private Date issuerTimestamp;
    private boolean result;

    public PccRequest2DTO() {
    }

    public PccRequest2DTO(Long acquierOrderId, Date acquierTimestamp, Long issuerOrderId, Date issuerTimestamp, boolean result) {
        this.acquierOrderId = acquierOrderId;
        this.acquierTimestamp = acquierTimestamp;
        this.issuerOrderId = issuerOrderId;
        this.issuerTimestamp = issuerTimestamp;
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

    public Long getIssuerOrderId() {
        return issuerOrderId;
    }

    public void setIssuerOrderId(Long issuerOrderId) {
        this.issuerOrderId = issuerOrderId;
    }

    public Date getIssuerTimestamp() {
        return issuerTimestamp;
    }

    public void setIssuerTimestamp(Date issuerTimestamp) {
        this.issuerTimestamp = issuerTimestamp;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
