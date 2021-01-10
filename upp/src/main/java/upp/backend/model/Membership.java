package upp.backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;

@Entity
public class Membership {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column
    private String name;
    
    @Column
    private double price;
    
    @Column
    private Date payedUntil;
    
	@ManyToOne(fetch = FetchType.LAZY)
    private User user;
	
	public Membership() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getPayedUntil() {
		return payedUntil;
	}

	public void setPayedUntil(Date payedUntil) {
		this.payedUntil = payedUntil;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Membership [id=" + id + ", name=" + name + ", price=" + price + ", payedUntil=" + payedUntil + ", user="
				+ user + "]";
	}
	
	
}