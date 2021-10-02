package com.examly.springapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="comments")
public class CommentModel {
	@Id
	@Column(nullable=false, length = 60)
	private String commentId;
	
	@Column(nullable=false, length=300)
	private String comment;

	@OneToOne
	private UserModel user;

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "CommentModel [commentId=" + commentId + ", comment=" + comment + ", user="+ user + "]";
	}
	
}
