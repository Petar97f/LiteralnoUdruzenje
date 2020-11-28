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

    public Card() {
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
}
