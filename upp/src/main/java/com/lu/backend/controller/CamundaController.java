package com.lu.backend.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lu.backend.dto.FormSubmissionDTO;
import com.lu.backend.dto.RegistrationDTO;
import com.lu.backend.dto.UserDTO;
import com.lu.backend.model.FormFieldsDTO;
import com.lu.backend.model.User;

@CrossOrigin("*")
@RestController
public class CamundaController {

	@Autowired
	private RuntimeService runtimeService;
	
    @Autowired
	TaskService taskService;
	
	@Autowired
	FormService formService;
	
	@GetMapping(value = "/{taskId}")
	public  ResponseEntity<?> getTask(@PathVariable("taskId") String taskId) {

    	//Service which provides access to Deployments, ProcessDefinitions and ProcessInstances.
				
    	return new ResponseEntity<>("fail", HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value = "start/{processName}")
	public @ResponseBody FormFieldsDTO startProcess(@PathVariable("processName") String processName) {
		ProcessInstance pi = runtimeService.startProcessInstanceByKey(processName);

		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
		
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		
		return new FormFieldsDTO(task.getId(), processName, properties, pi.getId());
	}
	
	/*@RequestMapping(value="/submitForm/{taskId}",method=RequestMethod.POST)
	public ResponseEntity<?> onSubmit(@RequestBody RegistrationDTO user, @PathVariable String taskId, HttpSession session, HttpServletRequest request){
		System.out.println("Submiting the form values.");
		//get task by Id
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();	
		
		if(task == null) {
			return new ResponseEntity<>("fail", HttpStatus.NOT_FOUND);
		}
		//get process instance from task
		String processInstanceId = task.getProcessInstanceId();
		//set values to variables
		runtimeService.setVariable(processInstanceId, "data", user);
		
		HashMap<String, Object> formFields = new HashMap<String, Object>();
		
		//submit form
		formService.submitTaskForm(task.getId(), formFields);
		
		
		
		return new ResponseEntity<>("fail", HttpStatus.NOT_FOUND);
	}*/
	
	@RequestMapping(value="/submitForm/{taskId}", method=RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> submitForm(@RequestBody ArrayList<FormSubmissionDTO> dto, @PathVariable String taskId) {
		HashMap<String, Object> map = this.mapListToDto(dto);
		//daj mi task RegisterForm
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		if (task == null) {
			return new ResponseEntity<>("fail", HttpStatus.NOT_FOUND);
		}
		//Daj mi koji je to proces instance (registration)
		String processInstanceId = task.getProcessInstanceId();
		System.out.println(task.getName());
		System.out.println(processInstanceId);
	
		System.out.println(dto);
		runtimeService.setVariable(processInstanceId, "registration", dto);
		formService.submitTaskForm(taskId, map);
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@SuppressWarnings("unused")
	private HashMap<String, Object> mapListToDto(ArrayList<FormSubmissionDTO> list) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		for(FormSubmissionDTO temp : list){
			map.put(temp.getFieldId(), temp.getFieldValue());
		}
		return map;
	}
	
}
