package com.app.trinsi.controller;

import com.app.trinsi.exceptions.MustUpdateHealthException;
import com.app.trinsi.exceptions.ResourceCantUpdateException;
import com.app.trinsi.exceptions.ResourceExistsException;
import com.app.trinsi.exceptions.WrongPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;


public abstract class BaseController {

	@ExceptionHandler
	public ResponseEntity<String> handlException(Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ AuthenticationException.class, UsernameNotFoundException.class })
	public ResponseEntity<String> authenticationException(Exception e) {
		return new ResponseEntity<>("Invalid login: " + e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public ResponseEntity<String> sectorFieldsExceptions(MethodArgumentNotValidException e) {
		String message = e.getBindingResult().getFieldError().getDefaultMessage();
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ ResourceExistsException.class, WrongPasswordException.class,
			org.springframework.dao.DataIntegrityViolationException.class, ResourceCantUpdateException.class,
			MustUpdateHealthException.class, IllegalStateException.class})
	public ResponseEntity<String> badRequest(Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

}
