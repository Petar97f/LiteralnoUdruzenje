package com.lu.backend.controller;

import com.lu.backend.dto.UserDTO;
import com.lu.backend.model.User;
import com.lu.backend.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin("*")
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    
    @GetMapping(value = "/getUser/{userId}")
	public UserDTO getUser(@PathVariable("userId") Long userId) {
		
    	User u = userService.findById(userId);
    	UserDTO userDTO = new UserDTO(u.getId(),u.getName(),u.getSurname(),u.getEmail(),u.getPassword(),u.getCity(),u.getTown());
    	return userDTO;
		
	}
    
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseEntity<?> loginUser(@RequestBody UserDTO user,HttpSession session,HttpServletRequest request){
		System.out.println("u:"+user.toString());
		System.out.println("u:"+user.getEmail());
		
		User logged = userService.findUserByEmail(user.getEmail());
		
		if(logged!=null ){
			
			HttpSession newSession = request.getSession();
		    newSession.setAttribute("logged", logged);
			System.out.println("u:"+logged.getEmail());
			return new ResponseEntity<>(logged,HttpStatus.OK);
		}
		return new ResponseEntity<>("fail", HttpStatus.NOT_FOUND);
	}
    
}
