package com.example.kp.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
public class Merchant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
    
    @Column
    private String merchantId;

    @Column
    private String password;

    @ElementCollection(targetClass=PaymentType.class)
    private List<PaymentType> paymentTypes;

    @Column
    private String successUrl;

    @Column
    private String failedUrl;

    @Column
    private String errorUrl;


    @Column
    private Long bankId;


    public Merchant() {
    }

    public Merchant(Long id, String merchantId, String password, String successUrl, String failedUrl, String errorUrl, Long bankId) {
        this.id = id;
        this.merchantId = merchantId;
        this.password = password;
        this.successUrl = successUrl;
        this.failedUrl = failedUrl;
        this.errorUrl = errorUrl;
        this.bankId = bankId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PaymentType> getPaymentTypes() {
        return paymentTypes;
    }

    public void setPaymentTypes(List<PaymentType> paymentTypes) {
        this.paymentTypes = paymentTypes;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public String getFailedUrl() {
        return failedUrl;
    }

    public void setFailedUrl(String failedUrl) {
        this.failedUrl = failedUrl;
    }

    public String getErrorUrl() {
        return errorUrl;
    }

    public void setErrorUrl(String errorUrl) {
        this.errorUrl = errorUrl;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }
}
