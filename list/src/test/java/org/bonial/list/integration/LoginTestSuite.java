package org.bonial.list.integration;

import java.util.Random;

import org.bonial.list.entity.User;
import org.bonial.list.rest.request.LoginRequest;
import org.bonial.list.rest.response.LoginResponse;
import org.json.JSONException;
import org.junit.Assert.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class LoginTestSuite extends TestCase {

	public void testLogin() throws JSONException {
		User user = new User();
		user.setLogin("admin");
		user.setPassword("admin");
		user.setId(1);

		LoginRequest request = new LoginRequest();
		request.setUser(user);

		RestTemplate restTemplate = new RestTemplate();
		LoginResponse response = restTemplate.postForObject("http://localhost:8080/user/login", request,
				LoginResponse.class);

		assertNotNull(response.getToken());
		assertNull(response.getErrorMessage());

	}

	public void testLoginWrongPassword() throws JSONException {
		User user = new User();
		user.setLogin("admin");
		user.setPassword("wrong");
		user.setId(1);

		LoginRequest request = new LoginRequest();
		request.setUser(user);

		RestTemplate restTemplate = new RestTemplate();
		LoginResponse response = restTemplate.postForObject("http://localhost:8080/user/login", request,
				LoginResponse.class);

		assertNull(response.getToken());
		assertNotNull(response.getErrorMessage());

	}
}
