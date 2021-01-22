package upp.backend.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import upp.backend.dto.LoginDTO;
import upp.backend.repository.ConfirmationTokenRepository;
import upp.backend.repository.UserRepository;
import upp.backend.security.TokenUtils;
import upp.backend.service.GenreService;
import upp.backend.service.UserDetailsServiceImpl;
import upp.backend.service.UserService;

@CrossOrigin("*")
@RestController
public class WorkController {

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
		
		
}
