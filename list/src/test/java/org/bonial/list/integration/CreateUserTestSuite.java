package org.bonial.list.integration;

import java.util.Random;

import org.bonial.list.entity.User;
import org.bonial.list.rest.request.CreateUserRequest;
import org.bonial.list.rest.response.CreateUserResponse;
import org.json.JSONException;
import org.junit.Assert.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class CreateUserTestSuite extends TestCase {

	public void testCreateUser() throws JSONException {
		User user = new User();
		user.setLogin("guest" + new Random().nextLong());
		user.setPassword("guest");
		user.setId(1);

		CreateUserRequest request = new CreateUserRequest();
		request.setUser(user);

		RestTemplate restTemplate = new RestTemplate();
		CreateUserResponse response = restTemplate.postForObject("http://localhost:8080/user/create", request,
				CreateUserResponse.class);

		assertNotNull(response.getUserId());
		assertNull(response.getErrorMessage());

	}

	public void testCreateUserDuplicate() throws JSONException {
		User user = new User();
		user.setLogin("guest" + new Random().nextLong());
		user.setPassword("guest");
		user.setId(1);

		CreateUserRequest request = new CreateUserRequest();
		request.setUser(user);

		RestTemplate restTemplate = new RestTemplate();
		CreateUserResponse response = restTemplate.postForObject("http://localhost:8080/user/create", request,
				CreateUserResponse.class);

		assertNotNull(response.getUserId());
		assertNull(response.getErrorMessage());

		CreateUserResponse response2 = restTemplate.postForObject("http://localhost:8080/user/create", request,
				CreateUserResponse.class);

		assertNull(response2.getUserId());
		assertNotNull(response2.getErrorMessage());

	}
}
