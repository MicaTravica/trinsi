package com.app.trinsi.service;

import com.app.trinsi.dto.PasswordChangeDTO;
import com.app.trinsi.exceptions.ResourceNotFoundException;
import com.app.trinsi.exceptions.UserNotFoundByUsernameException;
import com.app.trinsi.model.User;
import com.app.trinsi.model.UserHealth;
import com.app.trinsi.model.UserPlanner;

public interface UserService {

	User registration(User user) throws Exception;

	User findOne(Long id) throws ResourceNotFoundException;

	User update(User user) throws Exception;

	void changeUserPassword(PasswordChangeDTO pcDto, String username) throws Exception;

	User findOneByUsername(String name) throws UserNotFoundByUsernameException;

	void verifiedUserEmail(String token) throws ResourceNotFoundException;

	User updateUserHealth(UserHealth userHealth, String username) throws ResourceNotFoundException;

	User updateUserPlanner(UserPlanner userPlanner, String username) throws ResourceNotFoundException;
}
