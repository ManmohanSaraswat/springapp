package com.examly.springapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.CommentModel;
import com.examly.springapp.model.LoginModel;
import com.examly.springapp.model.PostModel;
import com.examly.springapp.model.UserModel;
import com.examly.springapp.repository.CommentRepository;
import com.examly.springapp.repository.PostRepository;
import com.examly.springapp.repository.UserRepository;
import com.examly.springapp.service.CommentService;
import com.examly.springapp.service.PostService;

@CrossOrigin
@RestController
public class CommentController {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired 
	UserRepository userRepo;
	@Autowired
	private PostRepository postRepo;
	
	@PostMapping("/image/{id}")
	public String addComment(@PathVariable String id, @RequestBody String comment){
		Optional<PostModel> check = postRepo.findById(id);
		if(check.isPresent()) {
			Optional<UserModel> userModel = userRepo.findById(check.get().getUserId());
			CommentModel commentModel = new CommentModel();
			commentModel.setComment(comment);
			commentModel.setUser(userModel.get());
			commentRepository.save(commentModel);
			return "done";
		}
		return "";
	}
}
