package upp.backend.handlers;

import java.util.List;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetTimeLimit implements TaskListener  {
	
	@Autowired
	private IdentityService identityService;
	
	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub
		//get user admin member
		List<User> users = identityService.createUserQuery().memberOfGroup("adminMember").list();
		User u = users.get(0);
		System.out.println("admin member"+ u.getId());
		//delegateTask.setVariable("oneMember", u.getId());
		String userNameToGiveOpinion = (String) delegateTask.getVariable("username");
		delegateTask.setVariable("user", userNameToGiveOpinion);
		delegateTask.getExecution().setVariable("oneMember", u.getId());
		delegateTask.getProcessEngineServices().getTaskService().setAssignee(delegateTask.getId(),u.getId());
		
	}
}
