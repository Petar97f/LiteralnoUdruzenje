package com.example.pcc.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private Long acquierOrderId;
    @Column
    private Date acquierTimestamp;
    @Column
    private Long cardId;

    public Request() {
    }

    public Request(Long id, Long acquierOrderId, Date acquierTimestamp, Long cardId) {
        this.id = id;
        this.acquierOrderId = acquierOrderId;
        this.acquierTimestamp = acquierTimestamp;
        this.cardId = cardId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
