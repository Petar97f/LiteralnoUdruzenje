package upp.backend.dto;

import java.util.List;

public class RegistrationDTO {
	private Long id;
	private String name;
	private String surname;
	private String email;
	private String password;
	private String city;
	private String country;
	private List<GenreDTO>genres;
	
	
	public RegistrationDTO() {
		
	}


	public RegistrationDTO(Long id, String name, String surname, String email, String password, String city,
			String country, List<GenreDTO> genres) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.city = city;
		this.country = country;
		this.genres = genres;
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


	public List<GenreDTO> getGenres() {
		return genres;
	}


	public void setGenres(List<GenreDTO> genres) {
		this.genres = genres;
	}
	
	
	
}
