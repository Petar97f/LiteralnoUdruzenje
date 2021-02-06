package upp.backend.handlers;

import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upp.backend.model.User;
import upp.backend.service.OpinionMemberService;
import upp.backend.service.UserService;

@Service
public class ValidateDecision  implements JavaDelegate {

	@Autowired
	private OpinionMemberService opinionMemberService;
	
	@Autowired
    private UserService userService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> formVariables = execution.getVariables();
		String autorId = formVariables.get("username").toString();
		int numberOfLoops = 0;
		if (execution.getVariable("numberOfLoops") != null) {
			numberOfLoops = (int) execution.getVariable("numberOfLoops");
		}
		
		boolean isSuitable = opinionMemberService.isSuitable(autorId, numberOfLoops - 1);
		if (isSuitable) {
			User user = userService.findByUsername(autorId);
			user.setIsApproved(true);
			userService.save(user);
		}
		execution.setVariable("isSuitable", isSuitable );
	}

}
