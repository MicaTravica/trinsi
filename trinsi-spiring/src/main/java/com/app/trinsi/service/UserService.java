package com.app.trinsi.service;

import java.util.Collection;

import com.app.trinsi.dto.PasswordChangeDTO;
import com.app.trinsi.exception.exceptions.ResourceNotFoundException;
import com.app.trinsi.exception.exceptions.UserNotFoundByUsernameException;
import com.app.trinsi.model.User;

public interface UserService {

	User registration(User user) throws Exception;

	User findOne(Long id) throws ResourceNotFoundException;

	User update(User user) throws Exception;

	void changeUserPassword(PasswordChangeDTO pcDto, String username) throws Exception;

	User findOneByUsername(String name) throws UserNotFoundByUsernameException;

	void verifiedUserEmail(String token) throws ResourceNotFoundException;

}
