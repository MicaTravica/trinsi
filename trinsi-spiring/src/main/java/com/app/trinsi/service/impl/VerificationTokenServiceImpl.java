package com.app.trinsi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.trinsi.exceptions.ResourceNotFoundException;
import com.app.trinsi.model.User;
import com.app.trinsi.model.VerificationToken;
import com.app.trinsi.repository.VerificationTokenRepository;
import com.app.trinsi.service.VerificationTokenService;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

	private final VerificationTokenRepository verificationTokenRepository;

	@Autowired
    public VerificationTokenServiceImpl(VerificationTokenRepository verificationTokenRepository) {
	    this.verificationTokenRepository = verificationTokenRepository;
    }

	@Override
    public VerificationToken getVerificationToken(String verificationToken) throws ResourceNotFoundException{
        return verificationTokenRepository.findByToken(verificationToken).orElseThrow(() -> new ResourceNotFoundException("Token"));
    }
     
    @Override
    public void create(User user, String token) {
        VerificationToken myToken = new VerificationToken(user, token);
        verificationTokenRepository.save(myToken);
    }
}
