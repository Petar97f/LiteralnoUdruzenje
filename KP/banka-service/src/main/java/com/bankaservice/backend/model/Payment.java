package com.bankaservice.backend.model;


import javax.persistence.*;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column
    private String MerchantId;
    
    @Column 
    private String paymentUrl;
    
    @Column 
    private Float amount;

    public Payment() {
    }
    

    public Payment(Long id, String merchantId, String paymentUrl, Float amount) {
		super();
		this.id = id;
		MerchantId = merchantId;
		this.paymentUrl = paymentUrl;
		this.amount = amount;
	}


	public Payment(Long id, String merchantId, String paymentUrl) {
		super();
		this.id = id;
		MerchantId = merchantId;
		this.paymentUrl = paymentUrl;
	}
    

	public String getMerchantId() {
		return MerchantId;
	}


	public void setMerchantId(String merchantId) {
		MerchantId = merchantId;
	}


	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public void setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
    }


	public Float getAmount() {
		return amount;
	}


	public void setAmount(Float amount) {
		this.amount = amount;
	}


	@Override
	public String toString() {
		return "Payment [id=" + id + ", MerchantId=" + MerchantId + ", paymentUrl=" + paymentUrl + ", amount=" + amount
				+ "]";
	}
	
    
}
