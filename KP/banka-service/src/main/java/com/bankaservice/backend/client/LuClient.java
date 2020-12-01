package com.bankaservice.backend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bankaservice.backend.dto.UserDTO;

@FeignClient(name = "upp")
public interface LuClient {
	@GetMapping(value = "/getUser/{userId}")
	public UserDTO getUser(@PathVariable("userId") Long userId);
}
