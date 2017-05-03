package org.bonial.list.rest.request;

public abstract class Request {
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
