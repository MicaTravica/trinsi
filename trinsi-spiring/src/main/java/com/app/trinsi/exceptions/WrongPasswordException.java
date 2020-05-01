package com.app.trinsi.exceptions;

public class WrongPasswordException extends Exception {

	private static final long serialVersionUID = 639659588110274590L;

	public WrongPasswordException() {
		super("Wrong password");
	}
}
