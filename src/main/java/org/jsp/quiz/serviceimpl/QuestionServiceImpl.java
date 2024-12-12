package org.jsp.quiz.serviceimpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.jsp.quiz.dao.QuestionDao;
import org.jsp.quiz.dto.QuestionDto;
import org.jsp.quiz.dto.QuizResponse;
import org.jsp.quiz.dto.TakeQuiz;
import org.jsp.quiz.entity.Question;

import org.jsp.quiz.exceptionclasses.InvalidQuestionIdException;
import org.jsp.quiz.exceptionclasses.NoQuestionFoundException;
import org.jsp.quiz.responsestructure.ResponseStructure;
import org.jsp.quiz.responsestructure.ResponseStructure.ResponseStructureBuilder;
import org.jsp.quiz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class QuestionServiceImpl  implements QuestionService {
	
	
	@Autowired
	private QuestionDao dao;
	
	@Override
	public ResponseEntity<?> saveQuestion(Question question) {
		
		 question= dao.saveQuestion(question);
		 return ResponseEntity
				 .status(HttpStatus.OK)
				 .body(
				 ResponseStructure
				 .builder()
				 .httpStatus(HttpStatus.OK.value())
				 .message("Saved Successfully")
				 .body(question)
				 .build()
				 );
	}

	@Override
	public ResponseEntity<?> findAllQuestions() {
//		List<Question> question = dao.findAllQuestions();
		List<Question> question = dao.findAllActiveQuestions();
		

		List<QuestionDto> dtolist = new ArrayList<>();
		
		for(Question q : question) {
			
			dtolist.add(new QuestionDto(q.getTitle(), q.getA(), q.getB(), q.getC(), q.getD()));
		}
		
		
		
		
		
		
		
		
		
		if(dtolist.isEmpty()) 
			throw NoQuestionFoundException.builder().message("No Question Found In The Database").build();
		
//		ResponseStructureBuilder<Object> structure = ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("All Questions Founded").body(question);
//		
//		throw ResponseEntity.status(HttpStatus.OK).body(structure);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(
						ResponseStructure
						.builder()
						.httpStatus(HttpStatus.OK.value())
						.message("All Questions Found Successfully...")
						.body(dtolist)
						.build()
						);
	}

	@Override
	public ResponseEntity<?> findQuestionById(int id) {
		Optional<Question> optional = dao.findQuestionById(id);
		if(optional.isEmpty()) 
			throw InvalidQuestionIdException.builder().message("Invalid Question id").build();
		
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("Question Found Successfully").body(optional.get()).build());
	}

	@Override
	public ResponseEntity<?> submitQuiz(List<QuizResponse> quizResponses) {
		
		int count = 0;
		
		for(QuizResponse qr : quizResponses) {
			Optional<Question> q = dao.findQuestionById(qr.getId());
//			if(q.isEmpty()) 
//				throw InvalidQuestionIdException.builder().message("Invalid Question Id Unable to Called this methos");
			
			Question question=q.get();
			
			if(question.getAns().equalsIgnoreCase(qr.getAns())) {
				count++;
			}
			
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("Submission Successfully").body("Your Score is :  " + count  ).build());
	}

	@Override
	public ResponseEntity<?> takeQuiz() {
		List<Question> questions = dao.takeQuiz();
		return null;
	}

	

//	@Override
//	public ResponseEntity<?> findAllQuestionForQuiz() {
//		List<Question> questions = dao.findAllQuestionForQuiz();
//		Random random = new Random();
//		
//		Set<TakeQuiz> takequiz = new HashSet();
//		for(Question q : questions) {
//			takequiz.add(new TakeQuiz(q.getId(),q.getTitle(),q.getA(),q.getB(),q.getC(),q.getD()));
//		}
//		if(takequiz.isEmpty()) 
//			throw NoQuestionFoundException.builder().message("Invalid Question Id").build();
//		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("Take the Quiz").body(takequiz.get(random.nextInt(takequiz.size()))).build());
//		
//	}

	
	
	
	

}
