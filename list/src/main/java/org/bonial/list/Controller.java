package org.bonial.list;

import java.util.Optional;

import org.bonial.list.dao.UserDAO;
import org.bonial.list.entity.User;
import org.bonial.list.util.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Controller
public class Controller {
	@Autowired
	UserDAO userDAO;

	@Autowired
	Authenticator authenticator;

	public Optional<Integer> createUser(User user) {
		return userDAO.createUser(user);
	}

	public Optional<String> login(User user) {
		User userDB = userDAO.getUser(user.getLogin());
		return authenticator.checkUser(user, userDB);
	}
}