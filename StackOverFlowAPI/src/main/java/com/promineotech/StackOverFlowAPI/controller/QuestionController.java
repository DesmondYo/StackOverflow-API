package com.promineotech.StackOverFlowAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.StackOverFlowAPI.entity.Question;
import com.promineotech.StackOverFlowAPI.service.QuestionService;

@RestController
@RequestMapping("/users/{userId}/questions")
public class QuestionController {

	@Autowired
	private QuestionService service;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> createQuestion(@RequestBody Question question) {
		return new ResponseEntity<Object>(service.createQuestion(question), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{questionId}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateQuestion(@RequestBody Question question, @PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.updateQuestion(question, id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Unable to update question.", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/{questionId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteQuestion(@PathVariable Long id) {
		try {
			service.removeQuestion(id);
			return new ResponseEntity<Object>("Successfully deleted question with id: " + id, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Unable to delete question.", HttpStatus.BAD_REQUEST);
		}
	}
	
	
	

}
