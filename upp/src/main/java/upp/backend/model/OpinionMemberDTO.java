package upp.backend.model;


public class OpinionMemberDTO {

    private Long id;

    private OpinionStatus opinion;

    private String autorId;

    private String memberId;

	public OpinionMemberDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public OpinionMemberDTO(Long id, OpinionStatus opinion, String autorId, String memberId) {
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
