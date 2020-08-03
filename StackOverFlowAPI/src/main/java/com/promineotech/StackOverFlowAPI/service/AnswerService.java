package com.promineotech.StackOverFlowAPI.service;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.StackOverFlowAPI.entity.Answer;
import com.promineotech.StackOverFlowAPI.entity.Question;
import com.promineotech.StackOverFlowAPI.entity.User;
import com.promineotech.StackOverFlowAPI.repository.AnswerRepository;
import com.promineotech.StackOverFlowAPI.repository.QuestionRepository;
import com.promineotech.StackOverFlowAPI.repository.UserRepository;

@Service
public class AnswerService {

	private static final Logger logger = LogManager.getLogger(AnswerService.class);

	@Autowired
	private AnswerRepository repo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private QuestionRepository questionRepo;

	public Iterable<Answer> getAllAnswers() {
		return repo.findAll();
	}

	public Answer createAnswer(Answer answer, Long userId, Long questionId) throws Exception {
		User user = userRepo.findOne(userId);
		Question question = questionRepo.findOne(questionId);
		if (user == null || question == null) {
			throw new Exception("The user or the question does not exist, please try again.");
		}
		answer.setDate(new Date());
		answer.setUser(user);
		answer.setQuestion(question);
		return repo.save(answer);
	}

	public void deleteAnswer(Long answerId) throws Exception {
		try {
			repo.delete(answerId);
		} catch (Exception e) {
			logger.error("Exception occured while trying to delete answer: " + answerId, e);
			throw new Exception("Unable to delete answer.");
		}
	}

}
