package upp.backend.handlers;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class BookValidation implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		//if () {
			execution.setVariable("validationSuccessful", false);
			return;
		//}
		
		//execution.setVariable("validationSuccessful", true);
	}

}
