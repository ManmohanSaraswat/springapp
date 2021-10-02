package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.LoginModel;
import com.examly.springapp.model.UserModel;
import com.examly.springapp.repository.LoginRepository;
import com.examly.springapp.repository.UserRepository;
import com.examly.springapp.response.UserCreationResponse;

@CrossOrigin
@RestController
public class SignupController {
	@Autowired
	UserRepository repo;
	
	@Autowired
	LoginRepository loginrepo;

	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public  @ResponseBody ResponseEntity<UserCreationResponse> saveUser(@RequestBody UserModel user){
		String email = user.getEmail();
		UserCreationResponse response = new UserCreationResponse();
		if(repo.findByEmail(email).size() == 0) {
			if(user.getFirstname().length() <= 2) {
				response.setResponse("false");
				response.setMessage("FistName Length Must be greater than 2 ");
				return new ResponseEntity<> (response, HttpStatus.BAD_REQUEST);
			}
				
			else if(user.getPassword().length() <= 5){
				response.setResponse("false");
				response.setMessage("Password Length Must be greater than 2 ");
				return new ResponseEntity<> (response,  HttpStatus.BAD_REQUEST);
			}else {
				LoginModel loginModel = new LoginModel();
				loginModel.setEmail(user.getEmail());
				loginModel.setPassword(user.getPassword());
				loginrepo.save(loginModel);
				repo.save(user);
				response.setMessage("User Created Successfully");
				response.setResponse("true");
				return new ResponseEntity<> (response, HttpStatus.OK);
			}
		}
		else {
			response.setMessage("Sorry! User Aready Exists");
			response.setResponse("false");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
}
