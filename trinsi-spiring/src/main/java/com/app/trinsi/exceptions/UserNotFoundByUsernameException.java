package com.app.trinsi.exceptions;

public class UserNotFoundByUsernameException extends Exception {

	private static final long serialVersionUID = 325420815083654461L;

	public UserNotFoundByUsernameException(String username) {
		super(String.format("User with %s username is not found!", username));
	}

}
