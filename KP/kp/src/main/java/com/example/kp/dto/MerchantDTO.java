package com.example.kp.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;

import com.example.kp.model.PaymentType;

public class MerchantDTO {
private Long id;
    
    private String merchantId;
    private String password;
    private String address;
    private String phoneNumber;
    private List<PaymentType> paymentTypes;
    private String successUrl;
    private String failedUrl;
    private String errorUrl;
    private Long bankId;
    
    public MerchantDTO() {
    	
    }
    
	public MerchantDTO(Long id, String merchantId, String password, String address, String phoneNumber,
			List<PaymentType> paymentTypes, String successUrl, String failedUrl, String errorUrl, Long bankId) {
		super();
		this.id = id;
		this.merchantId = merchantId;
		this.password = password;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.paymentTypes = paymentTypes;
		this.successUrl = successUrl;
		this.failedUrl = failedUrl;
		this.errorUrl = errorUrl;
		this.bankId = bankId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public Long getBankId() {
		return bankId;
	}

	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}
    
    
    
}
