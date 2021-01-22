package upp.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OpinionMember {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column  
    private OpinionStatus opinion;

    @Column
    private String autorId;

    @Column
    private String memberId;

	public OpinionMember() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OpinionMember(Long id, OpinionStatus opinion, String autorId, String memberId) {
		super();
		this.id = id;
		this.opinion = opinion;
		this.autorId = autorId;
		this.memberId = memberId;
	}

	public OpinionStatus getOpinion() {
		return opinion;
	}

	public void setOpinion(OpinionStatus opinion) {
		this.opinion = opinion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAutorId() {
		return autorId;
	}

	public void setAutorId(String autorId) {
		this.autorId = autorId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

}
