package com.sep.tim26.dto;


public class UserDTO {
	private Long id;
	private String name;
	private String surname;
	private String username;
	private String email;
	private String password;
	private String city;
	private String country;
	private Boolean activated;
	
	public UserDTO() {}
	
	
	
	public UserDTO(Long id, String name, String surname, String username, String email, String password, String city,
			String country, Boolean activated) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.email = email;
		this.password = password;
		this.city = city;
		this.country = country;
		this.activated = activated;
	}



	public UserDTO(Long id, String name, String surname, String email, String password, String city, String country,
			Boolean activated) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.city = city;
		this.country = country;
		this.activated = activated;
	}

	public UserDTO(Long id, String name, String surname, String email, String password, String city, String country) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.city = city;
		this.country = country;
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Boolean getActivated() {
		return activated;
	}

	public void setActivated(Boolean activated) {
		this.activated = activated;
	}



	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", name=" + name + ", surname=" + surname + ", username=" + username + ", email="
				+ email + ", password=" + password + ", city=" + city + ", country=" + country + ", activated="
				+ activated + "]";
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}

	


}
