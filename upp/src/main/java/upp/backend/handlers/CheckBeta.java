package upp.backend.handlers;

import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.form.FormField;
import org.springframework.stereotype.Service;

@Service
public class CheckBeta implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		Map<String, Object> formVariables = execution.getVariables();

		System.out.println(formVariables);
		boolean isBeta = (boolean) formVariables.get("isBeta");
		System.out.println(isBeta);
		
		if (isBeta) {
			System.out.println("here is beta true"+isBeta);
			execution.setVariable("isBeta", true);
		} else {
			System.out.println("here is beta false"+isBeta);
			execution.setVariable("isBeta", false);
		}
		
	
		
	}

}
