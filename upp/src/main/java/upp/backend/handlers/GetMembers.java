package upp.backend.handlers;

import java.util.List;
import java.util.stream.Collectors;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upp.backend.service.UserService;

@Service
public class GetMembers  implements JavaDelegate {

	@Autowired
	private UserService userService;
	
	@Autowired
	private IdentityService identityService;
	 
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		List<User> users = identityService.createUserQuery().memberOfGroup("members").list();

		
		execution.setVariable("numberOfMembers", users.size());
		
	}

}
