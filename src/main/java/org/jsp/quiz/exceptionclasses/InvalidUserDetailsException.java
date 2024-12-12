package org.jsp.quiz.exceptionclasses;

import lombok.Builder;

@Builder
public class InvalidUserDetailsException  extends RuntimeException{
	
    String message;
	
	public InvalidUserDetailsException() {
		
	}
	public InvalidUserDetailsException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}

}
