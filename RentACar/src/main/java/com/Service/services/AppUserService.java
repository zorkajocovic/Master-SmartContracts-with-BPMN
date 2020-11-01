package com.Service.services;

import java.util.List;

import org.camunda.bpm.engine.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Service.repositories.AppuserRepository;
import com.Service.security.JWTUtils;
import com.Service.security.MyUserDetailsService;
import com.Service.security.TokenDto;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import model.Appuser;

@Service
public class AppUserService {
	
	@Autowired
	AppuserRepository userRepository;
	
	@Autowired
	IdentityService identityService;

	@Autowired
	JWTUtils tokenUtils;
	
	public List<Appuser> getAll(){
		return userRepository.findAll();
	}
	
	public void addUser(Appuser u) {
		userRepository.save(u);
	}
	
	public void updateUser(Appuser u) {
		userRepository.save(u);
	}
	
	public Appuser getOne(Long id) {
		return userRepository.getOne(id);
	}
	
	public Appuser getbyEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
	
	public void deleteAllUser() {
		userRepository.deleteAll();
	}
	
	public Boolean existsUser(long id) {
		return userRepository.existsById(id);
	}

	public TokenDto generateToken(String username) {
		
		Appuser user = getbyEmail(username);
    	MyUserDetailsService customUserdetails = new MyUserDetailsService(user);
        String userToken = tokenUtils.generateToken(customUserdetails);            

        return new TokenDto(userToken);
	}
	
    public Appuser getCurrentUser() {
    	String currentUsername = "";
    	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	  if (!(auth instanceof AnonymousAuthenticationToken)) {
    		  currentUsername = auth.getName();
      		  System.out.println("username ulogovan: " + currentUsername);
    	  }
    	  else
      		  System.out.println(auth.getName());

          try {             
              Appuser user = getbyEmail(auth.getName());
              return user;
          } catch (Exception e) {
              return null;
          }
    }

	public Appuser checkUser(Appuser checkLoggedIn) {
		Appuser found = userRepository.findByEmail(checkLoggedIn.getEmail());
		if(found!=null){
			if(checkLoggedIn.getPassword().equals(found.getPassword()))
				return found;
			else return null;			
		}else{
			return null;
		}
	}
	
}
