package com.examly.springapp.controller;

import java.util.Optional;

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
import com.examly.springapp.repository.LoginRepository;
import com.examly.springapp.response.ResponseMessage;

@CrossOrigin
@RestController
public class LoginController {
	@Autowired
	LoginRepository loginrepo;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<ResponseMessage> checkUser(@RequestBody LoginModel user) {
		try {
			Optional<LoginModel> check = loginrepo.findById(user.getEmail());
			if (check.isPresent()) {
				LoginModel checkUser = check.get();
				if (user.getPassword().compareTo(checkUser.getPassword()) == 0)
					return new ResponseEntity<>(new ResponseMessage("User Logged in Successfully", "true"),
							HttpStatus.OK);
				else
					return new ResponseEntity<>(new ResponseMessage("Incorrect Password", "false"),
							HttpStatus.BAD_REQUEST);
			} else
				return new ResponseEntity<>(new ResponseMessage("User Doesn't Exists", "false"),
						HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseMessage("Exception", "false"), HttpStatus.BAD_REQUEST);
		}
	}
}
