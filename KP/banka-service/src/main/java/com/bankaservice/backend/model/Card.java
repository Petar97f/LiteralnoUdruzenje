package com.bankaservice.backend.model;

import javax.persistence.*;

@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column
    private Long clientId;

    @Column
    private String cardNumber;

    @Column
    private String expirationDate;

    @Column
    private String cvc;

    @Column
    private Float availableMoney;

    @Column
    private String pan;

    @Column
    private String securityCode;

    @Column
    private String merchantId;

    @Column
    private String merchantPassword;
    @ManyToOne( fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Bank bank;

    public Card() {
    }
    

    public Card(Long id, Long clientId, String cardNumber, String expirationDate, String cvc, Float availableMoney,
			String pan, String securityCode, String merchantId, String merchantPassword, Bank bank) {
		super();
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
		this.bank = bank;
	}


	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
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

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}
    
}
