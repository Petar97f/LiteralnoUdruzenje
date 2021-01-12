package com.bankaservice.backend.dto;

import java.util.Date;

public class SecurityCheckDTO {
	private String pan;
	private String securityCode;
	private String cardHolderName;
	private String expirationDate;
	private Long paymentId;
	
	public SecurityCheckDTO() {
		
	}
	
	public SecurityCheckDTO(String pan, String securityCode, String cardHolderName, String expirationDate,
			Long paymentId) {
		super();
		this.pan = pan;
		this.securityCode = securityCode;
		this.cardHolderName = cardHolderName;
		this.expirationDate = expirationDate;
		this.paymentId = paymentId;
	}
	

	public SecurityCheckDTO(String pan, String securityCode, String cardHolderName, String expirationDate) {
		super();
		this.pan = pan;
		this.securityCode = securityCode;
		this.cardHolderName = cardHolderName;
		this.expirationDate = expirationDate;
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

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}


	@Override
	public String toString() {
		return "SecurityCheckDTO{" +
				"pan='" + pan + '\'' +
				", securityCode='" + securityCode + '\'' +
				", cardHolderName='" + cardHolderName + '\'' +
				", expirationDate='" + expirationDate + '\'' +
				", paymentId=" + paymentId +
				'}';
	}
}
