package org.jsp.quiz.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.jsp.quiz.dao.Userdao;
import org.jsp.quiz.dto.AuthQuiz;
import org.jsp.quiz.entity.User;
import org.jsp.quiz.exceptionclasses.InvalidUserDetailsException;
import org.jsp.quiz.exceptionclasses.NoUserFoundException;
import org.jsp.quiz.responsestructure.ResponseStructure;
import org.jsp.quiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private Userdao dao;

	@Override
	public ResponseEntity<?> saveUser(User user) {
		user = dao.saveUser(user);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("User Saved Successfully").body(user).build());
	}

	@Override
	public ResponseEntity<?> findAllUser() {
		List<User> user = dao.findAllUser();
		if(user.isEmpty()) 
			throw NoUserFoundException.builder().message("No User Found").build();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("User Found Successfully").body(user).build());
	
		
	}

	@Override
	public ResponseEntity<?> findByEmailAndPassword(AuthQuiz auth) {
		Optional<User> optional = dao.findByEmailAndPassword(auth.getEmail(),auth.getPassword());
		if(optional.isEmpty()) 
			throw InvalidUserDetailsException.builder().message("Invalid Credentials").build();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("Login Sucessfully ...Please Take Quiz").body(optional.get()).build());
		
	}



	

}
