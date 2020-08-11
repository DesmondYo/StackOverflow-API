package com.promineotech.StackOverFlowAPI.service;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.StackOverFlowAPI.entity.Question;
import com.promineotech.StackOverFlowAPI.entity.User;
import com.promineotech.StackOverFlowAPI.repository.QuestionRepository;
import com.promineotech.StackOverFlowAPI.repository.UserRepository;

@Service
public class QuestionService {

	private static final Logger logger = LogManager.getLogger(QuestionService.class);

	@Autowired
	private QuestionRepository repo;

	@Autowired
	private UserRepository userRepo;

	public Question createQuestion(Question question, Long userId) throws Exception {
		User user = userRepo.findOne(userId);
		if (user == null) {
			throw new Exception("User not found.");
		}
		question.setDate(new Date());
		question.setUser(user);
		return repo.save(question);
	}

	public Question updateQuestion(Question question, Long userId) throws Exception {
		Question questionUpdate = repo.findOne(userId);
		if (questionUpdate == null) {
			throw new Exception("Question is not found, please try again");
		}
		questionUpdate.setContent(question.getContent());
		return repo.save(questionUpdate);
	}

	public void removeQuestion(Long userId) throws Exception {
		try {
			repo.delete(userId);
		} catch (Exception e) {
			logger.error("Exception occured while trying to delete the question: " + userId, e);
			throw new Exception("Unable to delete your question. Please try again!");
		}
	}

}