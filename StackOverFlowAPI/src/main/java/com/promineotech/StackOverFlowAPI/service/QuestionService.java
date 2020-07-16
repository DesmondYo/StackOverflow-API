package com.promineotech.StackOverFlowAPI.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.StackOverFlowAPI.entity.Question;

import com.promineotech.StackOverFlowAPI.repository.QuestionRepository;

@Service
public class QuestionService {

	private static final Logger logger = LogManager.getLogger(QuestionService.class);

	@Autowired
	private QuestionRepository repo;

	public Question createQuestion(Question question) {
		return repo.save(question);
	}

	public Question updateQuestion(Question question, Long id) throws Exception {
		try {
			Question oldQuestion = repo.findOne(id);
			oldQuestion.setContent(question.getContent());
			return repo.save(oldQuestion);
		} catch (Exception e) {
			logger.error("Exception occured while trying to update the question: " + id, e);
			throw new Exception("Unable to update your question. Please try again!");
		}
	}

	public void removeQuestion(Long id) throws Exception {
		try {
			repo.delete(id);
		} catch (Exception e) {
			logger.error("Exception occured while trying to delete the question: " + id, e);
			throw new Exception("Unable to delete your question. Please try again!");
		}
	}

}
