package upp.backend.handlers;

import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CheckBookInfo implements JavaDelegate{

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BookInfoHANDLER");
		Map<String, Object> formVariables = execution.getVariables();
		Boolean isValid = true;
		if (formVariables.get("name") == null ) {
			isValid = false;
		} else if (formVariables.get("synopsis") == null ) {
			isValid = false;
		} else if (formVariables.get("genre") == null ) {
			isValid = false;
		}
		
		if(!isValid) {
			execution.setVariable("validated", false);
		}
		else {
			execution.setVariable("validated", true);
		}
		System.out.println("VariableValid: "+execution.getVariable("validated"));
		
		
		
		
	}

}
