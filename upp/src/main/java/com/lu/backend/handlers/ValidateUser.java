package com.lu.backend.handlers;

import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;


@Service
public class ValidateUser implements JavaDelegate {

	@SuppressWarnings("unchecked")
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Validate User");
		Map<String, Object> formVariables = execution.getVariables();
		System.out.println(formVariables);
		//List<RegistrationDTO> registration = (List<RegistrationDTO>)execution.getVariable("registration");
		
		boolean isValid = false;
		if(!isValid) {
			execution.setVariable("isValid", false);
		}
		else {
			execution.setVariable("isValid", true);
		}
	}

}
