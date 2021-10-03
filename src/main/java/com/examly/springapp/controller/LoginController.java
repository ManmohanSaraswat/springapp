package com.examly.springapp.controller;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.LoginModel;
import com.examly.springapp.repository.LoginRepository;
import com.examly.springapp.response.ResponseMessage;
import com.examly.springapp.service.LoginService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@CrossOrigin
@RestController
public class LoginController {
	@Autowired
	LoginRepository loginrepo;
	@Autowired 
	private LoginService loginService;
	@Autowired
	private JwtUtil jwtutil;
	
	@Autowired 
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public @ResponseBody ResponseEntity<?> login(@RequestBody LoginModel user) {
		
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		}catch(UsernameNotFoundException e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("Bad Credentials");
		}
		UserDetails userDetails = this.loginService.loadUserByUsername(user.getEmail());
		String token = this.jwtutil.generateToken(userDetails);
		return new ResponseEntity<>(new ResponseMessage(token, userDetails.getUsername()),
				HttpStatus.OK);	
	}	
}