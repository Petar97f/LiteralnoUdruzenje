package upp.backend.handlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.camunda.bpm.engine.FilterService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.logging.Logger;
import org.camunda.bpm.engine.filter.Filter;

import upp.backend.model.OpinionMember;
import upp.backend.model.OpinionStatus;
import upp.backend.service.MembershipService;
import upp.backend.service.OpinionMemberService;
import upp.backend.service.UserService;

@Service
public class GetMembers  implements JavaDelegate {

	@Autowired
	private UserService userService;
	
	@Autowired
	private IdentityService identityService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private FilterService filterService;
	
	@Autowired
	private OpinionMemberService opinionMemberService;
	
	private static final Logger log = Logger.getLogger(GetMembers.class.getName());
	  
	 
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		List<User> users = identityService.createUserQuery().memberOfGroup("members").list();
		Map<String, Object> formVariables = execution.getVariables();
		String autorId = formVariables.get("username").toString();
		int numberOfLoops = 0;
		if (execution.getVariable("numberOfLoops") != null) {
			numberOfLoops = (int) execution.getVariable("numberOfLoops");
		}
		List<String> userMembers = new ArrayList<String>();
		for (User u : users) {
			if (execution.getVariable("isSuitable") != null) {
				if ((boolean)execution.getVariable("isSuitable") == false) {
					OpinionMember opinion = opinionMemberService.findByUserAndLoopMember(u.getId(),autorId, numberOfLoops - 1);
					if (opinion.getOpinion().equals(OpinionStatus.NOT_SUITABLE)) {
						userMembers.add(u.getId());
					}
				}
				
			} else {
				userMembers.add(u.getId());
				System.out.println("users ->" + u.getId());
			}
		}
		execution.setVariable("userMembers", userMembers);
		execution.setVariable("numberOfMembers", users.size());
		
	}

}
