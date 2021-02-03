package upp.backend.handlers;

import java.util.Map;
import java.util.logging.Logger;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upp.backend.model.User;
import upp.backend.service.EmailService;
import upp.backend.service.UserService;

@Service
public class NotifyByEmail  implements JavaDelegate {

	@Autowired
    IdentityService identityService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    private final Logger LOGGER = Logger.getLogger(NotifyByEmail.class.getName());
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		LOGGER.info("\n\n Send email " + execution.getId() + "\n\n");
		Map<String, Object> formVariables = execution.getVariables();
		String autorId = formVariables.get("username").toString();
		User user = new User();
		user = userService.findByUsername(autorId);
		if (formVariables.get("isSuitable").toString() != null) {
			boolean isSuitable = (boolean) formVariables.get("isSuitable");
			if (isSuitable) {
				emailService.sendEmail("Account Aproved",user,"Hi, you are now one step to become member, please pay membership in next two weeks");
			}
		} else {
		emailService.sendEmail("Account not suitable",user,"Hi, we need to inform you that your account is not approved");
		}
		//set member to zero
	}
}
