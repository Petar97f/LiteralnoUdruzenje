package upp.backend.controller;

import java.util.*;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import upp.backend.dto.FormSubmissionDTO;
import upp.backend.dto.RegistrationDTO;
import upp.backend.dto.RegistrationFormDTO;
import upp.backend.dto.UserDTO;
import upp.backend.model.FormFieldsDTO;
import upp.backend.model.GenresEnum;
import upp.backend.model.User;

@CrossOrigin("*")
@RequestMapping("")
@RestController
public class CamundaController {

	@Autowired
	private RuntimeService runtimeService;
	
    @Autowired
	private TaskService taskService;
	
	@Autowired
	private FormService formService;
	
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
		
		return new FormFieldsDTO(task.getId(), pi.getId(),properties);
	}
	
	@GetMapping(value = "getFormFields/{taskName}")
	public @ResponseBody FormFieldsDTO getForm(@PathVariable("taskName") String taskName) {
		Task task = taskService.createTaskQuery().taskName(taskName).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		
		return new FormFieldsDTO(task.getId(), processInstanceId, properties);
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
	
	@PostMapping(value="/submitForm/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity<?> submitForm(@RequestBody RegistrationFormDTO dto, @PathVariable String taskId) {
		HashMap<String, Object> map = this.mapListToDto(dto.getDto());
		//daj mi task RegisterForm
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		if (task == null) {
			return new ResponseEntity<>("fail", HttpStatus.NOT_FOUND);
		}
		//Daj mi koji je to proces instance (registration)
		String processInstanceId = task.getProcessInstanceId();

		runtimeService.setVariable(processInstanceId, "registration", dto);
		try {
			formService.submitTaskForm(taskId, map);
		} catch (Exception e) {
			return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		return new ResponseEntity<>("success",HttpStatus.OK);
    }
	
	private HashMap<String, Object> mapListToDto(ArrayList<FormSubmissionDTO> list) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		for(FormSubmissionDTO temp : list){
			map.put(temp.getFieldId(), temp.getFieldValue());
		}
		return map;
	}
	
}
