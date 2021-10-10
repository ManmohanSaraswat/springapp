package com.examly.springapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.examly.springapp.model.CommentModel;
import com.examly.springapp.response.ResponseMessage;
import com.examly.springapp.service.CommentService;
import com.examly.springapp.service.PostService;
import com.examly.springapp.service.UserService;

@CrossOrigin
@RestController
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	@Autowired 
	private UserService userService;
	@Autowired
	private PostService postService;
	
	@PostMapping("/image/addcomment")
	public ResponseEntity<ResponseMessage> addComment(@RequestBody CommentModel commentModel){
		String userId = commentModel.getUserId();
		String postId = commentModel.getPostId();
		String comment = commentModel.getComment();
		ResponseMessage message = new ResponseMessage();
		if(!postService.existsPost(postId)) {
			message.setMessage("Unable to find Post");
			message.setStatus(400);
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		if(!userService.existsUser(userId)) {
			message.setMessage("Unable to find User");
			message.setStatus(400);
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		if(commentService.addComment(userId, postId, comment)) {
			message.setMessage("Comment added successfully");
			message.setStatus(200);
			return new ResponseEntity<>(message, HttpStatus.OK);
		}else {
			message.setMessage("Unable to add comment");
			message.setStatus(400);
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}
}
