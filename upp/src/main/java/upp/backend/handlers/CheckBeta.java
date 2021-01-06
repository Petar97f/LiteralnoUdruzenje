package upp.backend.handlers;

import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CheckBeta implements JavaDelegate{

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> formVariables = execution.getVariables();
		System.out.println(formVariables);
		boolean isBeta = (boolean) formVariables.get("isBeta");
		System.out.println(isBeta);
		if (formVariables.get("isBeta") == null || !isBeta) { 
			execution.setVariable("isBeta", false);
		} 
		execution.setVariable("isBeta", true);
	}

}
