package upp.backend.model;


public class WorksDTO {

	private Long id;

    private String fileName;

	public WorksDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public WorksDTO(Long id, String fileName) {
		super();
		this.id = id;
		this.fileName = fileName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
    
    
    
    
}
