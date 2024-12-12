package org.jsp.quiz.daoimpl;

import java.util.List;
import java.util.Optional;

import org.jsp.quiz.dao.QuestionDao;
import org.jsp.quiz.dto.TakeQuiz;
import org.jsp.quiz.entity.Question;
import org.jsp.quiz.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;


@Repository     //it indicates interact to database
public class QuestionDaoImpl implements QuestionDao {
	
	@Autowired
	private QuestionRepository repository;

	@Override
	public Question saveQuestion(Question question) {
		return repository.save(question);
	}

	@Override
	public List<Question> findAllQuestions() {
		return repository.findAll();
	}

	@Override
	public Optional<Question> findQuestionById(int id) {
		return repository.findById(id);
	}

	@Override
	public List<Question> findAllActiveQuestions() {
		return repository.findAllActiveQuestions();
	}

//	@Override
//	public List<Question> findAllQuestionForQuiz() {
//		return repository.findAllQuestionForQuiz();
//	}

	@Override
	public List<Question> takeQuiz() {
		return repository.findRandomQuestions(PageRequest.of(0, 2));
	}

	
	
	

}
