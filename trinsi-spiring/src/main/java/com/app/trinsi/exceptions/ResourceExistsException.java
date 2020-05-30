package com.app.trinsi.exceptions;

public class ResourceExistsException extends Exception {

    public ResourceExistsException(String exist) {
		super(String.format("%s already exists", exist));
	}
}
