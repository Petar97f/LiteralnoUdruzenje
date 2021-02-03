package upp.backend.handlers;

import java.util.Arrays;
import java.util.List;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.impl.TaskQueryImpl;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upp.backend.service.UserService;


@Service
public class OnMembers implements JavaDelegate  {
	@Autowired
	private UserService userService;
	
	@Autowired
	private IdentityService identityService;
	
	@Autowired
	private TaskService taskService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("OnMembers started");
		List<User> users = identityService.createUserQuery().memberOfGroup("members").list();

		for (User u : users) {
			System.out.println("users that are members" + u.getId());
		}

		  for (Task task : taskService.createTaskQuery().list()) {
			  System.out.println("task" + task.getName());
		      }
		   
	}
}
