package org.bonial.list.rest.response;

public class CreateUserResponse extends Response {
	public static final String ERROR_MESSAGE = "Operation CREATE USER failed for '%s'";

	private Integer userId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	
	public CreateUserResponse() {

	}

	public CreateUserResponse(String error) {
		this.setErrorMessage(error);
	}

	public CreateUserResponse(Integer userId) {
		this.userId = userId;
	}

}
