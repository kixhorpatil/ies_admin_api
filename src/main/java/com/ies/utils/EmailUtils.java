package com.ies.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
 
@Component   //represents as a spring bean
public class EmailUtils {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public boolean sendEmail(String subject, String body, String to) {
		//logic to send email
		
		return true;
	}

}
