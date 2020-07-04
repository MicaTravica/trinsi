package com.app.trinsi.service;

import javax.mail.MessagingException;

public interface MailService {

	void newUser(String email, String token) throws MessagingException;

}
