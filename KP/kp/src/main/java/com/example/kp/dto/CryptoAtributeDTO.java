package com.example.kp.dto;

public class CryptoAtributeDTO {
	private Double amount;
	private String currency;
	private String recieveCurrency;
	
	public CryptoAtributeDTO (){
		
	}

	public CryptoAtributeDTO(Double amount, String currency, String recieveCurrency) {
		super();
		this.amount = amount;
		this.currency = currency;
		this.recieveCurrency = recieveCurrency;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getRecieveCurrency() {
		return recieveCurrency;
	}

	public void setRecieveCurrency(String recieveCurrency) {
		this.recieveCurrency = recieveCurrency;
	}
	
	
	
}
