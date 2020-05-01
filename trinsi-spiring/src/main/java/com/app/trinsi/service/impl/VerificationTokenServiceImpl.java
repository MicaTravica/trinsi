package com.app.trinsi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.trinsi.exception.exceptions.ResourceNotFoundException;
import com.app.trinsi.model.User;
import com.app.trinsi.model.VerificationToken;
import com.app.trinsi.repository.VerificationTokenRepository;
import com.app.trinsi.service.VerificationTokenService;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

	@Autowired
	private VerificationTokenRepository verificationTokenRepostory;
	
	@Override
    public VerificationToken getVerificationToken(String verificationToken) throws ResourceNotFoundException{
        return verificationTokenRepostory.findByToken(verificationToken).orElseThrow(() -> new ResourceNotFoundException("Token"));
    }
     
    @Override
    public void create(User user, String token) {
        VerificationToken myToken = new VerificationToken(user, token);
        verificationTokenRepostory.save(myToken);
    }
}
