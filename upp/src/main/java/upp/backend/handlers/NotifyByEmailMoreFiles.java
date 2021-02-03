package upp.backend.handlers;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upp.backend.model.User;
import upp.backend.service.EmailService;
import upp.backend.service.UserService;

@Service
public class NotifyByEmailMoreFiles  implements JavaDelegate{

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserService userService;
	 
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> formVariables = execution.getVariables();
		String autorId = formVariables.get("username").toString();
		String time = formVariables.get("sendFileTime").toString();
		int minute= Integer.parseInt(time);  
		Calendar now = Calendar.getInstance();
	    now.add(Calendar.MINUTE, minute);
		User user = new User();
		System.out.printf("NotifyByEmailMoreFiles: date ---> ", now);
	
		user = userService.findByUsername(autorId);
		emailService.sendEmail("More Files Request",user,"Hi, please send us more files in until of"+now.DAY_OF_MONTH+"/"+ now.MONTH + "/" + now.YEAR +",  "+ now.HOUR_OF_DAY +":"+ now.MINUTE);
	}

}
