package upp.backend.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import upp.backend.model.User;

public class MembershipDTO {
	
	private Long id;

    private String name;
    
    private double price;
    
    private Date payedUntil;
    
    private User user;
	
	private boolean isActive;

	public MembershipDTO() {
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "MembershipDTO [id=" + id + ", name=" + name + ", price=" + price + ", payedUntil=" + payedUntil
				+ ", user=" + user + ", isActive=" + isActive + "]";
	}
	
	
}
