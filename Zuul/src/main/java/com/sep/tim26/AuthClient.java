package com.sep.tim26;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sep.tim26.dto.LoginDTO;
import com.sep.tim26.dto.UserDTO;
import com.sep.tim26.security.JwtAuthenticationRequest;


@FeignClient(name = "upp")
public interface AuthClient {

	@PostMapping("/login")
	public ResponseEntity<String> login(@Valid @RequestBody LoginDTO loginDTO) throws IOException;
	@GetMapping(value = "/getUser/{userId}")
	public UserDTO getUser(@PathVariable("userId") Long userId);
	@GetMapping(value = "/getUsername/{userId}")
	public String getUsername(@PathVariable("userId") Long userId); 
		

    
}
