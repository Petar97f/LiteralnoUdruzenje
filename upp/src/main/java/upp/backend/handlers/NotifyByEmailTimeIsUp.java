package upp.backend.handlers;

import java.util.Calendar;
import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upp.backend.model.User;
import upp.backend.service.EmailService;
import upp.backend.service.UserService;

@Service
public class NotifyByEmailTimeIsUp  implements JavaDelegate{

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserService userService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> formVariables = execution.getVariables();
		String autorId = formVariables.get("username").toString();

		User user = new User();

		
		user = userService.findByUsername(autorId);
		emailService.sendEmail("Not in time",user,"Hi, you can not be member because you did not deliver files in time!");

	}

}
