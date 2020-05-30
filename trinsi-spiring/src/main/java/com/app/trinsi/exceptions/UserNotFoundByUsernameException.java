package com.app.trinsi.exceptions;

public class UserNotFoundByUsernameException extends Exception {

	public UserNotFoundByUsernameException(String username) {
		super(String.format("User with %s username is not found!", username));
	}

}
