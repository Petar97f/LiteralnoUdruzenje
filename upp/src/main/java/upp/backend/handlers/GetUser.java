package upp.backend.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.form.TaskFormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upp.backend.dto.FormSubmissionDTO;
import upp.backend.dto.RegistrationDTO;
import upp.backend.dto.RegistrationFormDTO;

@Service
public class GetUser implements TaskListener{

	@Autowired
	private IdentityService identityService;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub
		RegistrationFormDTO registrationFormDTO = (RegistrationFormDTO) delegateTask.getVariable("registration");
	    List<FormSubmissionDTO> fields=registrationFormDTO.getDto();
		TaskFormData taskFormData = delegateTask.getExecution().getProcessEngineServices().getFormService().getTaskFormData(delegateTask.getId());
		String username = "";
		for (FormSubmissionDTO f: fields) {
            if(f.getFieldId().equals("username")) {
            	username = (String) f.getFieldValue().toString();
            }
              
        }
		System.out.println("20 getuser"+username+" "+delegateTask.getId());
		delegateTask.setVariable("currentUser", username);
		delegateTask.getProcessEngineServices().getTaskService().setAssignee(delegateTask.getId(),username);
	}
	
}
