package com.promineotech.StackOverFlowAPI.service;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.promineotech.StackOverFlowAPI.entity.Credentials;
import com.promineotech.StackOverFlowAPI.entity.User;
import com.promineotech.StackOverFlowAPI.repository.UserRepository;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;
	
	public User register(Credentials cred) throws AuthenticationException {
		User user = new User();
		user.setUsername(cred.getUsername());
		user.setPassword(BCrypt.hashpw(cred.getPassword(), BCrypt.gensalt()));
		try {
			userRepository.save(user);
			return user;
		} catch (DataIntegrityViolationException e) {
			throw new AuthenticationException("Username is not valid! Please try again");
		}
	}
	
	public User login(Credentials cred) throws AuthenticationException {
		User foundUser = userRepository.findbyUsername(cred.getUsername());
		if(BCrypt.checkpw(cred.getPassword(), foundUser.getPassword())) {
			return foundUser;
		}
		throw new AuthenticationException("Incorrect Username or Password. Please try again!");
	}
	
}
