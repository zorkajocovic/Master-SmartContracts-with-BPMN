package com.Service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Service.dto.LoginRequestDto;
import com.Service.security.TokenDto;
import com.Service.services.AppUserService;

import model.Appuser;

@RestController
@RequestMapping(value = "api/user")public class AppUserController {
	
	@Autowired
	AppUserService userService;
	
	@PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> post(@RequestBody LoginRequestDto loginRequestDto) {
		
		try {
			  Appuser user = userService.getbyEmail(loginRequestDto.getEmail());
			  if (user != null) {
				  TokenDto tokenDto = userService.generateToken(user.getEmail());
				  return new ResponseEntity<>(tokenDto, HttpStatus.OK);
			  }
			  else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			  }			  
		} 
		catch(AuthenticationException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
    }
	
	@RequestMapping(value = "/getCurrentUser", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Appuser> getCurrentUser() {

		Appuser currentUser = userService.getCurrentUser();
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity<?> signout() {
		
        SecurityContextHolder.clearContext();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
