package org.bonial.list.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.bonial.list.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOMockImpl implements UserDAO {
	private static final Random RANDOM = new Random();
	Map<String, User> users = new HashMap<String, User>();

	@PostConstruct
	public void init() {
		User user = new User();
		user.setId(42);
		user.setLogin("admin");
		user.setPassword("admin");
		
		users.put("admin", user);
	}

	@Override
	public Optional<Integer> createUser(User user) {
		if (users.get(user.getLogin()) != null) {
			return Optional.empty();
		}

		Integer id = RANDOM.nextInt(Integer.MAX_VALUE);
		user.setId(id);
		users.put(user.getLogin(), user);

		return Optional.ofNullable(id);
	}

	@Override
	public User getUser(String login) {
		User user = users.get(login);
		if (user == null) {
			return null;
		}

		try {
			user = (User) user.clone();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

}
