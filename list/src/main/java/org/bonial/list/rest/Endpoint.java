package org.bonial.list.rest;

import org.bonial.list.entity.User;
import org.bonial.list.rest.request.CreateUserRequest;
import org.bonial.list.rest.request.LoginRequest;
import org.bonial.list.rest.response.CreateUserResponse;
import org.bonial.list.rest.response.LoginResponse;

public interface Endpoint {

	CreateUserResponse createUser(CreateUserRequest user);

	LoginResponse login(LoginRequest user);
}
