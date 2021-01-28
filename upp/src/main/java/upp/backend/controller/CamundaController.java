package upp.backend.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.ProcessEngine;
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
import org.camunda.bpm.engine.identity.Group;
import java.util.stream.Collectors;
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
	
	@Autowired
	private ProcessEngine processEngine;
	
	@Autowired
	private IdentityService identityService;
	

	@GetMapping(value = "start/{processName}")
	public @ResponseBody FormFieldsDTO startProcess(@PathVariable("processName") String processName) {
		ProcessInstance pi = runtimeService.startProcessInstanceByKey(processName);

		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
		
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		
		return new FormFieldsDTO(task.getId(), pi.getId(),properties);
	}
	

	
	@GetMapping(path = "/{taskId}", produces = "application/json")
	public @ResponseBody FormFieldsDTO getTask(@PathVariable String taskId) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		return new FormFieldsDTO(task.getId(), task.getProcessInstanceId(),properties);
	}

	@GetMapping(path = "/getTask", produces = "application/json")
	public @ResponseBody ResponseEntity<?> getTasks() {
		HashMap<String, String> message = new HashMap<String, String>();
		String username = this.identityService.getCurrentAuthentication().getUserId();
		System.out.println("Username"+username);
		if (username != null) {
			System.out.println("Username"+username);
			List<Task> nextTasks =  taskService.createTaskQuery().taskAssignee(username).active().list();
			System.out.println("nextTasks ===> "+nextTasks);
			if (!nextTasks.isEmpty()) {
				System.out.println("nextTasks"+nextTasks);
				Task nextTask = null;
				for (Task t : nextTasks) {
					if (t.getAssignee() != null && t.getAssignee().equals(username)) {
						nextTask = t;
						break;
					}
				}
				if (nextTask == null) {
					message.put("message", "success");
					message.put("status", "success");
					return new ResponseEntity<>(message,HttpStatus.OK);
				}
				TaskFormData taskFormData = formService.getTaskFormData(nextTask.getId());
				List<FormField> formFieldsList = taskFormData.getFormFields();
				return new ResponseEntity<>( new FormFieldsDTO(nextTask.getId(), nextTask.getProcessInstanceId(),formFieldsList) ,HttpStatus.OK);
			}
		}
		
		message.put("message", "not found");
		message.put("status", "fail");
		return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping(path = "/startProcess", produces = "application/json")
	public @ResponseBody ResponseEntity<?> startProcessUser() {
		System.out.println("Usao");
		String username = this.identityService.getCurrentAuthentication().getUserId();
		System.out.println("username 130--"+username);
		HashMap<String, String> message = new HashMap<String, String>();
		if (username != null) {
			List<Group> groups = this.identityService.createGroupQuery().groupMember(username).list();
			List<String> userIds = groups.stream().map(Group::getId).collect(Collectors.toList());
			if (userIds.contains("users")) {
				ProcessInstance pi = null;
				try {
					pi = runtimeService.startProcessInstanceByKey("upload_pdf");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("OVDE 1");
				}
				Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
				TaskFormData taskFormData = null;
				taskFormData = formService.getTaskFormData(task.getId());
				List<FormField> properties = taskFormData.getFormFields();
				return new ResponseEntity<>( new FormFieldsDTO(task.getId(), task.getProcessInstanceId(),properties), HttpStatus.OK);
			}
		}
		message.put("message", "not found");
		message.put("status", "fail");
		return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(value="/submitForm/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity<?> submitForm(@RequestBody RegistrationFormDTO dto, @PathVariable String taskId) {
		HashMap<String, Object> map = this.mapListToDto(dto.getDto());
		HashMap<String, String> message = new HashMap<String, String>();
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
			
			message.put("message", "fail");
			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		System.out.println("////submit form ////");
		String username = this.identityService.getCurrentAuthentication().getUserId();
		System.out.println("-----> username =>>>>"+username);
		//String username = (String) runtimeService.getVariable(processInstanceId, "currentUser");
		//System.out.println("currentUsrt"+username);
		ProcessInstance pi=runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
	    if(pi!=null) {
	    	if (runtimeService.getVariable(processInstanceId, "isValid") != null) {
	    		boolean isValid = (boolean) runtimeService.getVariable(processInstanceId, "isValid");
		    	if(!isValid) {
		    		message.put("message", "Invaid email or username");
		    		message.put("status", "fail");
					return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		    	}
	    	}
	    	
	    }
		System.out.println("tASK LIST IF NEXTASK ID SEND");
		
		if (username != null) {
			List<Task> nextTasks = taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId()).list();
			System.out.println("nextTasks ===> "+nextTasks);
			if (!nextTasks.isEmpty()) {
				System.out.println("nextTasks"+nextTasks);
				Task nextTask = null;
				for (Task t : nextTasks) {
					if (t.getAssignee() != null && t.getAssignee().equals(username)) {
						nextTask = t;
						break;
					}
				}
				if (nextTask == null) {
					message.put("message", "success");
					message.put("status", "success");
					return new ResponseEntity<>(message,HttpStatus.OK);
				}
				TaskFormData taskFormData = formService.getTaskFormData(nextTask.getId());
				List<FormField> formFieldsList = taskFormData.getFormFields();
				return new ResponseEntity<>( new FormFieldsDTO(nextTask.getId(), nextTask.getProcessInstanceId(),formFieldsList) ,HttpStatus.OK);
			}
		}
		

		message.put("message", "success");
		message.put("status", "success");
		return new ResponseEntity<>(message,HttpStatus.OK);
    }
	
	private HashMap<String, Object> mapListToDto(ArrayList<FormSubmissionDTO> list) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		for(FormSubmissionDTO temp : list) {
			map.put(temp.getFieldId(), temp.getFieldValue());
		}
		return map;
	}
	
}
