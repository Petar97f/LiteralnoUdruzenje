package com.lu.backend.controller;

import com.lu.backend.dto.UserDTO;
import com.lu.backend.model.User;
import com.lu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    
    @GetMapping(value = "/getUser/{userId}")
	public UserDTO getUser(@PathVariable("userId") Long userId) {
		
    	User u = userService.findById(userId);
    	UserDTO userDTO = new UserDTO(u.getId(),u.getName(),u.getSurname(),u.getEmail(),u.getPassword());
    	return userDTO;
		
	}
    
}
