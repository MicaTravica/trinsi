package com.app.trinsi.service.impl;

import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.app.trinsi.service.MailService;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSender mailSender;

	@Async
	@Override
	public void newUser(String email, String token) throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mmHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
		String message = "<html><head><meta charset=\"UTF-8\"></head>" + "<body><h3>Trinsi app - Wellcome!</h3><br>"
				+ "<div><p>You can verify your email "
				+ "<a target=\"_blank\" href = \"http://localhost:8080/trinsi/user/verify/" + token
				+ "\"><u>here</u></a>!.</p></div></body></html>";
		mmHelper.setText(message, true);
		mmHelper.setTo(email);
		mmHelper.setSubject("Trinsi app - Wellcome!");
		mailSender.send(mimeMessage);
	}

}
