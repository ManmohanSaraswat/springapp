package com.examly.springapp.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.UserModel;

@CrossOrigin
@RestController
public class Login {
	@Autowired
	UserRepository repo;
	@RequestMapping(value="/getAll", method=RequestMethod.GET)
	public List<UserModel> getAll(){
		List<UserModel> users = (List<UserModel>) repo.findAll();
		return users;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public String deleteUser(@PathVariable String id) {
		repo.deleteById(id);
		return "User Deleted" + id;
	}
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public UserModel home(@RequestBody UserModel user) {
		String email = user.getEmail();
		if(repo.findByEmail(email) == null) {
			repo.save(user);
			return user;
		}
		else {
			return null;
		}
	}
}
