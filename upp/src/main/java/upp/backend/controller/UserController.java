package upp.backend.controller;

import org.camunda.bpm.engine.task.Task;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import upp.backend.dto.GenreDTO;
import upp.backend.dto.LoginDTO;
import upp.backend.dto.RegistrationDTO;
import upp.backend.dto.UserDTO;
import upp.backend.model.ConfirmationToken;
import upp.backend.model.User;
import upp.backend.repository.ConfirmationTokenRepository;
import upp.backend.repository.UserRepository;
import upp.backend.security.TokenUtils;
import upp.backend.service.GenreService;
import upp.backend.service.UserDetailsServiceImpl;
import upp.backend.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.http.HttpHeaders;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.HashMap;

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


	@Autowired
	AuthenticationManager authenticationManager;


	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	TokenUtils tokenUtils;

	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;

	@Autowired
	private UserRepository userRepository;
    
	@Autowired
    ProcessEngine processEngine;
	
	@Autowired
	private IdentityService identityService;
	
    @GetMapping(value = "/getUser/{userId}")
	public UserDTO getUser(@PathVariable("userId") Long userId) {
		
    	User u = userService.findById(userId);
    	UserDTO userDTO = new UserDTO(u.getId(),u.getName(),u.getSurname(),u.getUsername(),u.getEmail(),u.getPassword(),u.getCity(),u.getCountry(),u.getActivated());
    	return userDTO;
		
	}

//
//    @GetMapping(value = "/getFormRegister")
//	public @ResponseBody FormFieldsDTO getRegisterForm() {
//    	ProcessInstance pi = runtimeService.startProcessInstanceByKey("Process_reg");
//
//		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
//
//		TaskFormData tfd = formService.getTaskFormData(task.getId());
//		List<FormField> properties = tfd.getFormFields();
//
//		return new FormFieldsDTO(task.getId(), properties, pi.getId());
//	}
//



	@PostMapping("/login")
	public ResponseEntity<String> login(@Valid @RequestBody LoginDTO loginDTO) throws IOException {
		try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					loginDTO.getEmail(), loginDTO.getPassword());
			SecurityContextHolder.getContext().setAuthentication(token);

			authenticationManager.authenticate(token);
			
			UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getEmail());
			System.out.println("details"+details);
			User userr=userDetailsService.findUserByEmail(loginDTO.getEmail());
			System.out.println(userr);
			if(!userr.getActivated()){
				return new ResponseEntity<>("fail", HttpStatus.UNAUTHORIZED);
			}
			return new ResponseEntity<String>(tokenUtils.generateToken(details), HttpStatus.OK);
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
	}
	
	
	

//	@RequestMapping(value="/register/{taskId}",method=RequestMethod.POST)
//	public ResponseEntity<?> register(@RequestBody RegistrationDTO user, @PathVariable String taskId, HttpSession session, HttpServletRequest request){
//		System.out.println("u:" + user.toString());
//		System.out.println("u:" + user.getEmail());
//		//to-do
//		//to complete task
//		//Task taskTemp = taskService.createTaskQuery().taskId(taskId).singleResult();
//		//check if task exists
//		//if(taskTemp == null) {
//		//	return new ResponseEntity<>("The task doesn't exist!", HttpStatus.NOT_FOUND);
//		//}
//		//taskService.complete(taskId);
//
//		//processEngine().getTaskService() .complete("someTaskIdHere");
//
//		System.out.println("Submiting the form values.");
//
//		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
//
//		if(task == null) {
//			return new ResponseEntity<>("fail", HttpStatus.NOT_FOUND);
//		}
//
//		//List<FormField> properties = ;
//		//formService.submitTaskForm(taskId, properties);
//
//		User u = new User(user.getId(),user.getName(),user.getSurname(),user.getEmail(),user.getPassword(),user.getCity(),user.getCountry());
//		userService.save(u);
//		for(GenreDTO g : user.getGenres()) {
//			System.out.println(g);
//			System.out.println(u.getGenres());
//			u.getGenres().add(genreService.convertFromDTO(g));
//			genreService.findById(g.getId()).getUsers().add(u);
//		}
//		System.out.println(u);
//		return new ResponseEntity<>("success", HttpStatus.CREATED);
//	}

	@GetMapping("/confirm-account")
	public ResponseEntity<?> confirmUserAccount(@RequestParam("token")String confirmationToken)
	{
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
	
		if(token != null) {
			String processId = token.getProcesInstanceId();
			System.out.println("token process instance id: "+ processId);
			runtimeService.setVariable(processId, "confirm", true);
		}
		else {
			return new ResponseEntity<String>("fail", HttpStatus.CONFLICT);
		}
		//String processId = this.jwtProvider.getProcessIdFromToken(token);
		//runtimeService.setVariable(processId, "confirm", true);
		//redirect na login
		HttpHeaders headersRedirect = new HttpHeaders();
		headersRedirect.add("Location", "http://localhost:3000");
		headersRedirect.add("Access-Control-Allow-Origin", "*");
		return new ResponseEntity<byte[]>(null, headersRedirect, HttpStatus.FOUND);
		//return new ResponseEntity<String>("success", HttpStatus.CREATED);
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
	
	@PostMapping(value="/upload/{processInstanceId}", produces = "application/json")
	public ResponseEntity<?> onFileUpload(@PathVariable String processInstanceId, @RequestBody MultipartFile file)  {
		HashMap<String, String> message = new HashMap<String, String>();
		System.out.println(processInstanceId);
		System.out.println("File upload start");
		String fileDownloadUrl = "";
		//String username = (String) runtimeService.getVariable(processInstanceId, "currentUser");
		String username = this.identityService.getCurrentAuthentication().getUserId();
		
		System.out.println("username" + username);
		System.out.println(file);
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		System.out.println("Filename"+fileName);
		message.put("message", "success");
		return new ResponseEntity<>(message, HttpStatus.CREATED);
	}
    
}
