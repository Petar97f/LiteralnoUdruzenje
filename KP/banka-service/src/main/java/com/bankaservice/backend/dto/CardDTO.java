package com.bankaservice.backend.dto;

import com.bankaservice.backend.model.Bank;

import javax.persistence.*;

public class CardDTO {

    private Long id;

    private Long clientId;

    private String cardNumber;

    private String expirationDate;

    private String cvc;

    private Float availableMoney;

    private String pan;

    private String securityCode;

    private String merchantId;

    private String merchantPassword;

    private Long bankId;

    public CardDTO() {
    }

    public CardDTO(Long id, Long clientId, String cardNumber, String expirationDate, String cvc, Float availableMoney, String pan, String securityCode, String merchantId, String merchantPassword, Long bankId) {
        this.id = id;
        this.clientId = clientId;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvc = cvc;
        this.availableMoney = availableMoney;
        this.pan = pan;
        this.securityCode = securityCode;
        this.merchantId = merchantId;
        this.merchantPassword = merchantPassword;
        this.bankId = bankId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public Float getAvailableMoney() {
        return availableMoney;
    }

    public void setAvailableMoney(Float availableMoney) {
        this.availableMoney = availableMoney;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantPassword() {
        return merchantPassword;
    }

    public void setMerchantPassword(String merchantPassword) {
        this.merchantPassword = merchantPassword;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }
}
