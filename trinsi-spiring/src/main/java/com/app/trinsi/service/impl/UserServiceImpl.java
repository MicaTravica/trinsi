package com.app.trinsi.service.impl;

import java.util.Calendar;
import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.trinsi.dto.PasswordChangeDTO;
import com.app.trinsi.exception.exceptions.ResourceExistsException;
import com.app.trinsi.exception.exceptions.ResourceNotFoundException;
import com.app.trinsi.exception.exceptions.UserNotFoundByUsernameException;
import com.app.trinsi.exception.exceptions.WrongPasswordException;
import com.app.trinsi.model.User;
import com.app.trinsi.model.VerificationToken;
import com.app.trinsi.repository.UserRepository;
import com.app.trinsi.service.MailService;
import com.app.trinsi.service.UserService;
import com.app.trinsi.service.VerificationTokenService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private VerificationTokenService verificationTokenService;

	@Autowired
	private MailService mailService;
	
	@Override
	public User registration(User user) throws Exception {
		if(userRepository.findByUsername(user.getUsername()).isPresent()) {
			throw new ResourceExistsException("Username");
		} else if(userRepository.findByEmail(user.getEmail()).isPresent()) {
			throw new ResourceExistsException("Email");
		}
		user.registration();
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		user = userRepository.save(user);
		String token = UUID.randomUUID().toString();
		verificationTokenService.create(user, token);
		mailService.newUser(user.getEmail(), token);
		return user;
	}

	@Override
	public User findOne(Long id) throws ResourceNotFoundException  {
		return userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User"));
	}

	@Override
	public User update(User user) throws Exception {
		User userToUpdate = userRepository.findById(user.getId()).orElseThrow(() -> new ResourceNotFoundException("User"));
		if(!user.getUsername().equals(userToUpdate.getUsername()) && userRepository.findByUsername(user.getUsername()).isPresent()) {
			throw new ResourceExistsException("Username");
		}
		userToUpdate.update(user);
		return userRepository.save(userToUpdate);
	}

	@Override
	public void changeUserPassword(PasswordChangeDTO pcDto, String username) throws Exception {
		User user = userRepository.findByUsername(username).orElseThrow(() ->  new UserNotFoundByUsernameException(username));
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		if(bcpe.matches(pcDto.getOldPassword(), user.getPassword())) {
			user.setPassword(bcpe.encode(pcDto.getNewPassword()));
			userRepository.save(user);
		}else {
			throw new WrongPasswordException();
		}
	}
	
	@Override
	public void verifiedUserEmail(String token) throws ResourceNotFoundException {
		VerificationToken verificationToken = verificationTokenService.getVerificationToken(token);
	    User user = verificationToken.getUser();
	    Calendar cal = Calendar.getInstance();
	    if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
	    	throw new ResourceNotFoundException("User");
	    }
	    user.setVerified(true);
		userRepository.save(user);
	}
	
	@Override
	public User findOneByUsername(String name) throws UserNotFoundByUsernameException {
		return userRepository.findByUsername(name).orElseThrow(() ->  new UserNotFoundByUsernameException(name));
	}

}
