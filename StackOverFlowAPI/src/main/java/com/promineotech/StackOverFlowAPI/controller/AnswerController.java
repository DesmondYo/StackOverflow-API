package com.promineotech.StackOverFlowAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.StackOverFlowAPI.entity.Answer;
import com.promineotech.StackOverFlowAPI.service.AnswerService;

@RestController
@RequestMapping("/users/{userId}/questions/{questionId}/answers")
public class AnswerController {

	@Autowired
	private AnswerService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> getAllAnswers() {
		return new ResponseEntity<Object>(service.getAllAnswers(), HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> createAnswer(@RequestBody Answer answer, @PathVariable Long userId,
			@PathVariable Long questionId) {
		try {
			return new ResponseEntity<Object>(service.createAnswer(answer, userId, questionId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/{answerId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteAnswer(@PathVariable Long answerId) {
		try {
			service.deleteAnswer(answerId);
			return new ResponseEntity<Object>("Deleted answer with id: " + answerId, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

}
