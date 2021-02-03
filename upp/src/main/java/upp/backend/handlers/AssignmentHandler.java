package upp.backend.handlers;

import java.util.List;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upp.backend.service.UserService;

@Service
public class AssignmentHandler implements TaskListener  {

	@Autowired
	private UserService userService;
	
	@Autowired
	private IdentityService identityService;
	
	@Autowired
	private TaskService taskService;
	
	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub
		String username = (String) delegateTask.getVariable("oneMember");
		String userNameToGiveOpinion = (String) delegateTask.getVariable("username");
		String pdfToGiveOpinion = (String) delegateTask.getVariable("pdf");
		delegateTask.setAssignee(username);
		delegateTask.setVariable("userName", userNameToGiveOpinion);
		delegateTask.setVariable("pdf", pdfToGiveOpinion);
	}

}

