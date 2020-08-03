package com.promineotech.StackOverFlowAPI.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.promineotech.StackOverFlowAPI.entity.Following;
import com.promineotech.StackOverFlowAPI.entity.User;
import com.promineotech.StackOverFlowAPI.repository.UserRepository;

@Service
public class UserService {

	private static final Logger logger = LogManager.getLogger(UserService.class);

	@Autowired
	private UserRepository repo;

	public User getUser(Long id) {
		return repo.findOne(id);
	}

	public Following follow(Long userId, Long followId) throws Exception {
		User user = repo.findOne(userId);
		User follow = repo.findOne(followId);
		if (user == null || follow == null) {
			throw new Exception("Your follower does not exist");
		}
		user.getFollowing().add(follow);
		repo.save(user);
		return new Following(user);
	}

	public Following getFollowedUsers(Long userId) throws Exception {
		User user = repo.findOne(userId);
		if (user == null) {
			throw new Exception("Your follower does not exist");
		}
		return new Following(user);
	}

	public User updateUserInfo(User user, Long id) throws Exception {
		try {
			User updateUser = repo.findOne(id);
			updateUser.setUsername(user.getUsername());
			updateUser.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
			updateUser.setQuestions(user.getQuestions());
			updateUser.setAnswers(user.getAnswers());
			return repo.save(updateUser);
		} catch (Exception e) {
			logger.error("Exception occured while trying to update the user. Please try again : " + id, e);
			throw new Exception("Unable to update the user.");
		}
	}

	public void deleteUser(Long id) throws Exception {
		try {
			repo.delete(id);
		} catch (Exception e) {
			logger.error("Exception occured while trying to update the user. Please try again : " + id, e);
			throw new Exception("Unable to delete the user");
		}
	}

}

