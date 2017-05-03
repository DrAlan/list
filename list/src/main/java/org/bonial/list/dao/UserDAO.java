package org.bonial.list.dao;

import java.util.Optional;

import org.bonial.list.entity.User;
import org.springframework.stereotype.Repository;

public interface UserDAO {
	Optional<Integer> createUser(User user);

	User getUser(String login);
}
