package upp.backend.handlers;

import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class ValidateMembersOpinion implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		boolean isValid = false;
		Map<String, Object> formVariables = execution.getVariables();
		if (formVariables.get("suitable") == null ) {
			isValid = false;
		} else if (formVariables.get("notSuitable") == null ) {
			isValid = false;
		} else if (formVariables.get("moreDocs") == null ) {
			isValid = false;
		} else if (formVariables.get("comment") == null ) {
			isValid = false;
		} else {
			isValid = true;
		}
		
		System.out.println("validOpinipon====> "+isValid);
		execution.setVariable("validOpinion", isValid);
	}

}
