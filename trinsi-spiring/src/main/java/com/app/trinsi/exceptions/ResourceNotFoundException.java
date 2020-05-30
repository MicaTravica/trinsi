package com.app.trinsi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ResourceNotFoundException extends Exception {

	public ResourceNotFoundException(String entity) {
		super(String.format("%s is not found!", entity));
	}
}
