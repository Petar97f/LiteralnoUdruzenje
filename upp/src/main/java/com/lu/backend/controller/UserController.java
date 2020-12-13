package com.lu.backend.controller;

import com.lu.backend.dto.GenreDTO;
import com.lu.backend.dto.RegistrationDTO;
import com.lu.backend.dto.UserDTO;
import com.lu.backend.model.FormFieldsDTO;
import com.lu.backend.model.User;
import com.lu.backend.service.GenreService;
import com.lu.backend.service.UserService;

import java.util.List;
import java.util.Set;

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

//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin("*")
@RestController
public class UserController {
    @Autowired
    private UserService userService;
	@Autowired
	private GenreService genreService;
	@Autowired
	private RuntimeService runtimeService;
	
    @Autowired
	TaskService taskService;
	
	@Autowired
	FormService formService;
    
    @GetMapping(value = "/getUser/{userId}")
	public UserDTO getUser(@PathVariable("userId") Long userId) {
		
    	User u = userService.findById(userId);
    	UserDTO userDTO = new UserDTO(u.getId(),u.getName(),u.getSurname(),u.getEmail(),u.getPassword(),u.getCity(),u.getTown());
    	return userDTO;
		
	}
    
    @GetMapping(value = "/getFormRegister")
	public @ResponseBody FormFieldsDTO getRegisterForm() {
    	ProcessInstance pi = runtimeService.startProcessInstanceByKey("Process_reg");

		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
		
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		
		return new FormFieldsDTO(task.getId(), properties, pi.getId());
	}
    
    
    
	@PostMapping(value="/login")
	public ResponseEntity<?> loginUser(@RequestBody UserDTO user, HttpSession session, HttpServletRequest request){
		System.out.println("u:" + user.toString());
		System.out.println("u:" + user.getEmail());
		
		User logged = userService.findUserByEmail(user.getEmail());
		
		if(logged!=null ){
			HttpSession newSession = request.getSession();
			newSession.setAttribute("logged", logged);
			System.out.println("u:" + logged.getEmail());
			return new ResponseEntity<>(logged, HttpStatus.OK);
		}
		return new ResponseEntity<>("fail", HttpStatus.NOT_FOUND);
	}
	

	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ResponseEntity<?> register(@RequestBody RegistrationDTO user, HttpSession session, HttpServletRequest request){
		System.out.println("u:" + user.toString());
		System.out.println("u:" + user.getEmail());
		//to-do
		System.out.println(user.getGenres());
		User u = new User(user.getId(),user.getName(),user.getSurname(),user.getEmail(),user.getPassword(),user.getCity(),user.getTown());
		userService.save(u);
		for(GenreDTO g : user.getGenres()) {
			System.out.println(g);
			System.out.println(u.getGenres());
			u.getGenres().add(genreService.convertFromDTO(g));
			genreService.findById(g.getId()).getUsers().add(u);
		}
		System.out.println(u);
		return new ResponseEntity<>("success", HttpStatus.CREATED);
	}
	
	
	
	
	@RequestMapping(value="/register-reader",method=RequestMethod.POST)
	public ResponseEntity<?> registerReader(@RequestBody UserDTO user, HttpSession session, HttpServletRequest request){
		System.out.println("u:" + user.toString());
		System.out.println("u:" + user.getEmail());
		//to-do
		
		return new ResponseEntity<>("fail", HttpStatus.NOT_FOUND);
	}
	
	
	@RequestMapping(value="/register-writter",method=RequestMethod.POST)
	public ResponseEntity<?> registerWritter(@RequestBody UserDTO user, HttpSession session, HttpServletRequest request){
		System.out.println("u:" + user.toString());
		System.out.println("u:" + user.getEmail());
		//complite task -- 
		
		//to-do
		// šalje email sa linkom za potvrdu koji koristi jednokratni hash 
		
		return new ResponseEntity<>("fail", HttpStatus.NOT_FOUND);
	}
	
    
}
