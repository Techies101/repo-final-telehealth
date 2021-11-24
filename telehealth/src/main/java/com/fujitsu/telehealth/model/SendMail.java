package com.fujitsu.telehealth.model;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	private String userEmail;
	private String hash;

	public SendMail() {
		super();
	}

	public SendMail(String userEmail, String hash) {
		super();
		this.userEmail = userEmail;
		this.hash = hash;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String emailTo) {
		this.userEmail = emailTo;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public boolean sendEmail(String htmlTemplate, String email, String password) {

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
		} catch (Exception e) {
			System.out.println("Send Email Error:" + e);
		}

		return successSend;
	}

}
