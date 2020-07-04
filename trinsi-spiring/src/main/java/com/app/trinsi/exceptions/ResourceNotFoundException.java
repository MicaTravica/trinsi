package com.app.trinsi.exceptions;

public class ResourceNotFoundException extends Exception {

	public ResourceNotFoundException(String entity) {
		super(String.format("%s is not found!", entity));
	}
}
