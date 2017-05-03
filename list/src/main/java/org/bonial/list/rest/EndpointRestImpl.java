package org.bonial.list.rest;

import java.util.Optional;

import org.bonial.list.Controller;
import org.bonial.list.entity.User;
import org.bonial.list.rest.request.CreateUserRequest;
import org.bonial.list.rest.request.LoginRequest;
import org.bonial.list.rest.response.CreateUserResponse;
import org.bonial.list.rest.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(method = RequestMethod.POST)
public class EndpointRestImpl implements Endpoint {
	@Autowired
	Controller controller;

	@Override
	@RequestMapping(value = "/user/create")
	public CreateUserResponse createUser(@RequestBody CreateUserRequest request) {
		CreateUserResponse response = new CreateUserResponse();
		
		User user = request.getUser();
		
		if (request == null | user == null) {
			response.setErrorMessage(String.format(CreateUserResponse.ERROR_MESSAGE, "null"));
			return response;
		}

		Optional<Integer> id = controller.createUser(user);

		if (id.isPresent()) {
			response.setUserId(id.get());
		} else {
			response.setErrorMessage(String.format(CreateUserResponse.ERROR_MESSAGE, user.getLogin()));
		}
		return response;

	}

	@Override
	@RequestMapping(value = "/user/login")
	public LoginResponse login(@RequestBody LoginRequest request) {
		LoginResponse response = new LoginResponse();
		
		User user = request.getUser();
		Optional<String> token = controller.login(user);
		
		if (token.isPresent()) {
			response.setToken(token.get());
		} else {
			response.setErrorMessage(String.format(LoginResponse.ERROR_MESSAGE, user.getLogin()));
		}
		return response;
	}

}
