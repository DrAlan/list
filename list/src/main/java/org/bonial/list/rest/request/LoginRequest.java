package org.bonial.list.rest.request;

import org.bonial.list.entity.User;

public class LoginRequest extends Request {
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
