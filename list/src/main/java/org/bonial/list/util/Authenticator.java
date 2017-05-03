package org.bonial.list.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.bonial.list.entity.User;
import org.springframework.stereotype.Component;

@Component
public class Authenticator {
	
	//<token, userId>
	Map<String, Integer> tokens = new HashMap<>();
	
	public Optional<String> checkUser(User user, User userDB){
		user.setId(userDB.getId());
		if(!user.equals(userDB)){
			return Optional.empty();
		}
		
		String token = generateToken();
		tokens.put(token, userDB.getId());
		
		return Optional.of(token);
	}
	
	private String generateToken(){
		return UUID.randomUUID().toString();
	}
}
