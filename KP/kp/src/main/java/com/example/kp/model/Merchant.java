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
    private String name;

    @Column
    private String password;
    
    @Column
    private String address;
    
    @Column 
    private String phoneNumber;
    
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
    
    

    public Merchant(Long id, String merchantId, String password, String address, String phoneNumber,
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
    
}
