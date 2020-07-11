package com.promineotech.StackOverFlowAPI.repository;

import org.springframework.data.repository.CrudRepository;

import com.promineotech.StackOverFlowAPI.entity.Vote;

public interface VoteRepository extends CrudRepository<Vote, Long> {

}
