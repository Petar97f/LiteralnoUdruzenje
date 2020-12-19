package upp.backend.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String city;
    @Column
    private String country;
    @Column
    private Boolean activated;
    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private Set<Genre> genres;

    @Column
	private UserRole userRole;

    public User() {
    }
    
    
    public User(Long id, String name, String surname, String email, String password, String city, String country, UserRole userRole) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.city = city;
		this.country = country;
		this.activated = false;
		this.genres = new HashSet<Genre>();
		this.userRole = userRole;
	}


	public User(Long id, String name, String surname, String email, String password, String city, String country,
			Set<Genre> genres) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.city = city;
		this.country = country;
		this.activated = false;
		this.genres = genres;
	}


	public User(Long id, String name, String surname, String email, String password, String city, String country,Boolean activate) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.city = city;
		this.country = country;
		this.activated = activate;
		this.genres = new HashSet<Genre>();
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

	public Set<Genre> getGenres() {
		return genres;
	}

	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}


	public Boolean getActivated() {
		return activated;
	}

	public void setActivated(Boolean activated) {
		this.activated = activated;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
}
