package upp.backend.handlers;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmedEmail implements TaskListener {

	@Autowired
    RuntimeService runtimeService;
	
	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub
		System.out.println("Confirmedmail user task");
		String tokenId  = (String) delegateTask.getExecution().getVariable("tokenId");
		 
		delegateTask.complete();
	
	}

}
