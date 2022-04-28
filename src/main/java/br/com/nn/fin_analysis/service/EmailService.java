package br.com.nn.fin_analysis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService{
	
	@Autowired
	private JavaMailSender emailSender;
	
	public void sendSimpleEmail() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("from@example.com");
		message.setTo("to@example.com");
		message.setSubject("teste asdfasdf");
		message.setText("teste de funcionalidade");
		emailSender.send(message);
	}
	
}
