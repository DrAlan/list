package org.bonial.list.rest.response;

public class LoginResponse extends Response {
	public static final String ERROR_MESSAGE = "Operation LOGIN failed for '%s'";

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
