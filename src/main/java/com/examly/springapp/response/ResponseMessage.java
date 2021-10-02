package com.examly.springapp.response;

public class ResponseMessage {
	  private String message;
	  private String status;
	  public ResponseMessage(String message, String status) {
		  this.message = message;
		  this.setStatus(status);
	  }
	  public ResponseMessage(String message) {
	    this.message = message;
	  }

	  public String getMessage() {
	    return message;
	  }

	  public void setMessage(String message) {
	    this.message = message;
	  }
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}