package com.example.banka2service.dto;

import java.util.Date;

public class IssuerDTO {
    private Long issuerOrderId;
    private Date issuerTimestamp;

    public IssuerDTO() {
    }

    public IssuerDTO(Long issuerOrderId, Date issuerTimestamp) {
        this.issuerOrderId = issuerOrderId;
        this.issuerTimestamp = issuerTimestamp;
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
}
