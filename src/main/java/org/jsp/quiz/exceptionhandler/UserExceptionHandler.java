package org.jsp.quiz.exceptionhandler;

import org.jsp.quiz.exceptionclasses.InvalidUserDetailsException;
import org.jsp.quiz.responsestructure.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {
	
	@ExceptionHandler(InvalidUserDetailsException.class)
	public ResponseEntity<?>  noUserFoundExceptionHandler(InvalidUserDetailsException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("No User Found In Database").body("User not found in database").build());
	}

}
