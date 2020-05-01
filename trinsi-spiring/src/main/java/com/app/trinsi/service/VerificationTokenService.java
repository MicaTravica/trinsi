package com.app.trinsi.service;

import com.app.trinsi.exceptions.ResourceNotFoundException;
import com.app.trinsi.model.User;
import com.app.trinsi.model.VerificationToken;

public interface VerificationTokenService {

	void create(User user, String token);

	VerificationToken getVerificationToken(String verificationToken) throws ResourceNotFoundException;

}
