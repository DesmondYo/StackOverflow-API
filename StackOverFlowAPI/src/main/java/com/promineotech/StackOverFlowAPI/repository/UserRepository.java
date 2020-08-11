package com.promineotech.StackOverFlowAPI.repository;

import org.springframework.data.repository.CrudRepository;

import com.promineotech.StackOverFlowAPI.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	public User findByUsername(String username);

}
