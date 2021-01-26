package upp.backend.handlers;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import upp.backend.dto.FormSubmissionDTO;
import upp.backend.dto.RegistrationFormDTO;
import upp.backend.model.User;
import upp.backend.model.Works;
import upp.backend.repository.UserRepository;
import upp.backend.service.UserService;
import upp.backend.service.WorksService;

public class SavePdf  implements JavaDelegate {

	 @Autowired
	 private UserService userService;
	 @Autowired
	 private WorksService worksService;
	 
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Save pdf to dir");
		System.out.println("PATHHHHHHHHHH" + (String)execution.getVariable("pdf"));
		Map<String,Object> formVariables = execution.getVariables();
		String username = formVariables.get("username").toString();
		String pdfs = formVariables.get("username").toString();
		List<String> pdfList = Arrays.asList(pdfs.split(","));
		User user = userService.findByUsername(username);
		for (String pdf : pdfList) {
			String[] parts = pdf.split("/");
			Works works = new Works();
			works.setFilePath(pdf);
			works.setFileName(parts[parts.length - 1]);
			works.setUser(user);
			System.out.print(parts[parts.length - 1]);
			worksService.save(works);
		}

		
	}

}
