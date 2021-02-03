package upp.backend.handlers;

import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Service;

@Service
public class AssignTask implements TaskListener{

	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub
		Map<String, Object> formVariables = delegateTask.getVariables();
		String autorId = formVariables.get("username").toString();
		delegateTask.setVariable("currentUser", autorId);
	}

}
