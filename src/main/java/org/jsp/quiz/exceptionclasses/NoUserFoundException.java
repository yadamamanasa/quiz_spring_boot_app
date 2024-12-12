package org.jsp.quiz.exceptionclasses;

import lombok.Builder;

@Builder
public class NoUserFoundException extends RuntimeException {
	
   private String message;
	
	public NoUserFoundException(){
		
	}
	public NoUserFoundException(String message) {
		this.message = message;
		
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}

}
