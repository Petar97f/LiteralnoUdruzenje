package upp.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pricelist {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
	
	@Column
	private String typeMemebership;
	
	@Column
	private double price;

	
	public Pricelist() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pricelist(Long id, String typeMemebership, double price) {
		super();
		this.id = id;
		this.typeMemebership = typeMemebership;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return typeMemebership;
	}

	public void setType(String type) {
		this.typeMemebership = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
}
