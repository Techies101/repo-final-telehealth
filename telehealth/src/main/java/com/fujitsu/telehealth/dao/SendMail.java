package com.fujitsu.telehealth.dao;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	
	private String userEmail;
	private String hash;
	
	public SendMail(String userEmail, String hash) {
		super();
		this.userEmail = userEmail;
		this.hash = hash;
	}
	
	public boolean sendMail(String htmlTemplate) {
		
		String email = "";
		String password = "";
		boolean successSend = false;
		
		Properties props = new Properties();
		
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props, new Authenticator() {
			
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication(email, password);
			}
		});
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(email));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
			message.setSubject("Account Verification");
			message.setContent(htmlTemplate, "text/html");
			Transport.send(message);
			successSend = true;
			return successSend;
		}catch(Exception e) {
			System.out.println("Send Email Error:" +e);
		}
		
		return successSend;
	}
	
}
