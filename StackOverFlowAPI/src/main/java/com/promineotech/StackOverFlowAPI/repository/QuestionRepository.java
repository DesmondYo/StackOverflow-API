package com.promineotech.StackOverFlowAPI.repository;

import org.springframework.data.repository.CrudRepository;

import com.promineotech.StackOverFlowAPI.entity.Question;

public interface QuestionRepository extends CrudRepository<Question, Long> {

}
