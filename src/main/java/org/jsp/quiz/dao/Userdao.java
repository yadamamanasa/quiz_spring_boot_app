package org.jsp.quiz.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.quiz.entity.User;

public interface Userdao {

	User saveUser(User user);

	List<User> findAllUser();

	Optional<User> findByEmailAndPassword(String email, String password);


}
