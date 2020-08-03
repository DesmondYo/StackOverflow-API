package com.promineotech.StackOverFlowAPI.entity;

import java.util.Set;

public class Following {

	private Long id;
	private Set<User> following;

	public Following(User user) {
		following = user.getFollowing();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<User> getFollowing() {
		return following;
	}

	public void setFollowing(Set<User> following) {
		this.following = following;
	}
}
