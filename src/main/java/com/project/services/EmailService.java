package com.project.services;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	public boolean sendEmail(String message,String subject,String to,String from) {
		
		//variable for gmail
		 boolean f = false;
		
		String host = "smtp.gmail.com";
		
		/* get the system properties */
		Properties properties = System.getProperties();
		
	/* setting important information for properties */
		
	/* putting properties to system */
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable","true");
		properties.put("mail.smtp.auth","true");
		
		//step:1:get the session object
		
		Session session =Session.getInstance(properties, new Authenticator(){

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return  new PasswordAuthentication("jayraut05@gmail.com","9373492049");
			}
		
		});
		session.setDebug(true);
		
		//compose the message
		
		MimeMessage mimeMessage = new MimeMessage(session);
		
	
		try {
			//email sending from 
			mimeMessage.setFrom(from);
			
		//adding reciptant to message
		mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		
		//adding subject to message
		mimeMessage.setSubject(subject);
		
		//adding text to mesaage
		
		mimeMessage.setContent(message,"text/html");
		
		//step:3 send the message using transport
		Transport.send(mimeMessage);
		 System.out.println("Email send sucessfully");
		 f =true;
		 
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return f;
		
	}
      
	
}
