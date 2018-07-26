package com.vishal.login.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rest/hello")
@RestController
public class HelloController {

	@GetMapping("/all")
	public String hello(){
		return "Hello World!";
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/secured/all")
	public String helloSecured(){
		return "Hello Secured	 World!";
	}
	
	@PreAuthorize("hasAnyRole('USER')")
	@GetMapping("/secured/one")
	public String helloUser(){
		return "Hello User!";
	}
}
