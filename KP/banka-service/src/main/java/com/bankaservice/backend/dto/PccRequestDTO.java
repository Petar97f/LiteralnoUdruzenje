package com.bankaservice.backend.dto;

import java.util.Date;

public class PccRequestDTO {
	private Long acquierOrderId;
	private Date acquierTimestamp;
	private CardDTO cardDTO;
	
	
	public PccRequestDTO() {
		
	}


	public PccRequestDTO(Long acquierOrderId, Date acquierTimestamp) {
		super();
		this.acquierOrderId = acquierOrderId;
		this.acquierTimestamp = acquierTimestamp;
	}

	public PccRequestDTO(Long acquierOrderId, Date acquierTimestamp, CardDTO cardDTO) {
		this.acquierOrderId = acquierOrderId;
		this.acquierTimestamp = acquierTimestamp;
		this.cardDTO = cardDTO;
	}

	public CardDTO getCardDTO() {
		return cardDTO;
	}

	public void setCardDTO(CardDTO cardDTO) {
		this.cardDTO = cardDTO;
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


	
	
}
