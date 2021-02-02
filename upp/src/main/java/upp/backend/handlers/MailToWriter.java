package upp.backend.handlers;

import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import upp.backend.dto.FormSubmissionDTO;
import upp.backend.model.User;
import upp.backend.service.EmailService;
import upp.backend.service.UserService;

public class MailToWriter implements JavaDelegate {

	 @Autowired
	 private UserService userService;

	 @Autowired
	 private EmailService emailService;
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Mail to writter service :::");	
		Map<String,Object> formVariables = execution.getVariables();
		 
	       // Treba se korisnik prvo ulogovati 
	       User user=userService.findUserByEmail(formVariables.get("email").toString());
	        

	       emailService.sendToWriter("Notification to upload",user,"w");
		
		
	}

}
