package com.example.bitcoin.model;

public class RequestDTO {
    private String amount;
    private String address;

    public RequestDTO() {
    }

    public RequestDTO(String amount, String address) {
        this.amount = amount;
        this.address = address;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "RequestDTO{" +
                "amount='" + amount + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
