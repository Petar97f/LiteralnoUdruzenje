package upp.backend.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class RegistrationFormDTO implements Serializable{
	ArrayList<FormSubmissionDTO> dto;
	
	public RegistrationFormDTO() {
		
	}

	public RegistrationFormDTO(ArrayList<FormSubmissionDTO> dto) {
		super();
		this.dto = dto;
	}

	public ArrayList<FormSubmissionDTO> getDto() {
		return dto;
	}

	public void setDto(ArrayList<FormSubmissionDTO> dto) {
		this.dto = dto;
	}

	@Override
	public String toString() {
		return "RegistrationFormDTO [dto=" + dto + "]";
	}
	
	
}
