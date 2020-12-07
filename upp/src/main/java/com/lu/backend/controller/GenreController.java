package com.lu.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.lu.backend.service.UserService;

@CrossOrigin(origins = "*")
@RestController
public class GenreController {

	 @Autowired
	 private UserService userService;
	
	
	
}
