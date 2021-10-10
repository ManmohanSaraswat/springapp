package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.CommentModel;
import com.examly.springapp.repository.CommentRepository;
import com.examly.springapp.repository.PostRepository;

@Service
public class CommentService {
	@Autowired 
	private CommentRepository commentRepository;
	
	public List<CommentModel> getComment(String postId){
		return commentRepository.getCommentModelByPostId(postId);
	}
	public boolean addComment(String userId, String postId, String comment) {
		CommentModel commentModel = new CommentModel();
		commentModel.setComment(comment);
		commentModel.setPostId(postId);
		commentModel.setUserId(userId);
		try {
			commentRepository.save(commentModel);
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	public boolean deleteComment(String userId, String postId, String comment) {
		return false;
	}
	public boolean updateComment(String userId, String postId, String comment) {
		return false;
	}
	
}
