package upp.backend.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Genre {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
	
	@Column
	private String name;
	
    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private Set<User> users;

	public Genre() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Genre(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Genre [id=" + id + ", name=" + name + "]";
	}
	
	
	
}
