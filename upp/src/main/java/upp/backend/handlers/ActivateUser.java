package upp.backend.handlers;

import java.util.List;
import java.util.logging.Logger;

import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upp.backend.model.User;
import upp.backend.service.UserService;

@Service
public class ActivateUser implements JavaDelegate {

    @Autowired
    private UserService userService;
    @Autowired
   	private TaskService taskService;
    private final Logger LOGGER = Logger.getLogger(ActivateUser.class.getName());
    
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		LOGGER.info("\n\n ActivateUser " + execution.getId() + "\n\n");
		String username = (String)execution.getVariable("username");
		boolean confirm = (boolean)execution.getVariable("confirm");
		System.out.println("UserConfirmationService");
		System.out.println("username"+ username);
		System.out.println("confirm"+confirm);
		
		if (confirm) {
			User user = this.userService.findByUsername(username);
			user.setActivated(true);
			LOGGER.info("\n\n User " + user.getId() + "is activated" + "\n\n");
			this.userService.save(user);
			execution.setVariable("isActivated", true);
		}
		
	}

}
