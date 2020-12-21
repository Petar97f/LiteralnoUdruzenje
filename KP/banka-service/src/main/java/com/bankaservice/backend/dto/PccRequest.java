package com.bankaservice.backend.dto;

import java.util.Date;

public class PccRequest {
	private Long acquierOrderId;
	private Date acquierTimestamp;
	private Long cardId;
	
	
	public PccRequest() {
		
	}


	public PccRequest(Long acquierOrderId, Date acquierTimestamp) {
		super();
		this.acquierOrderId = acquierOrderId;
		this.acquierTimestamp = acquierTimestamp;
	}
	

	public PccRequest(Long acquierOrderId, Date acquierTimestamp, Long cardId) {
		super();
		this.acquierOrderId = acquierOrderId;
		this.acquierTimestamp = acquierTimestamp;
		this.cardId = cardId;
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


	public Long getCardId() {
		return cardId;
	}


	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}
	
	
}
