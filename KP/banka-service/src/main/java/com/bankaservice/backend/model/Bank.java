package com.bankaservice.backend.model;

import javax.persistence.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column
    private String address;

    @Column
    private String name;

    @OneToMany( fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Set<Card> clientCards = new HashSet<>();
    
    public Bank() {
    	
    }
    
    
	public Bank(Long id, String address, String name) {
		super();
		this.id = id;
		this.address = address;
		this.name = name;
		this.clientCards = new HashSet<>();
				}


	public Bank(Long id, String address, String name, Set<Card> clientCards) {
		super();
		this.id = id;
		this.address = address;
		this.name = name;
		this.clientCards = clientCards;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Set<Card> getClientCards() {
		return clientCards;
	}


	public void setClientCards(Set<Card> clientCards) {
		this.clientCards = clientCards;
	}

	

    

}
