package upp.backend.handlers;

import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class ValidatePdf  implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> formVariables = execution.getVariables();
		boolean validationSuccessful = true;
		String pdf = formVariables.get("pdf").toString();
		System.out.println("bOOK VALIDATION => " + pdf);
		if (formVariables.get("pdf") == null ) {
			validationSuccessful = false;
		}

		if(!validationSuccessful) {
			execution.setVariable("validationSuccessful", false);
		}
		else {
			execution.setVariable("validationSuccessful", true);
		}

	}
}
